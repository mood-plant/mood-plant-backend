package team.tourgini.moodplant.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import team.tourgini.moodplant.service.dto.CreateThreadRequest;
import team.tourgini.moodplant.service.dto.Message;
import team.tourgini.moodplant.service.dto.MessageList;
import team.tourgini.moodplant.service.dto.RecommendDto;
import team.tourgini.moodplant.service.dto.RunRequest;
import team.tourgini.moodplant.service.dto.RunResponse;
import team.tourgini.moodplant.service.dto.ThreadDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

@Slf4j
@Component
@RequiredArgsConstructor
public class Analyzer {

    private static final String USER_ROLE = "user";
    private static final String THREAD_URL = "https://api.openai.com/v1/threads";
    private static final String RUN_URL = "https://api.openai.com/v1/threads/%s/runs";

    private final Scraper scraper;
    private final ObjectMapper objectMapper;

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.assistant.id}")
    private String assistantId;

    public RecommendDto analyze(String uri) {
        String text = scraper.extractText(uri)
                .stream()
                .map(t -> t.split(" "))
                .flatMap(Arrays::stream)
                .filter(StringUtils::hasText)
                .map(String::trim)
                .distinct()
                .collect(Collectors.joining(" "));
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(USER_ROLE, "I’m trying to extract keywords from the scraped data given below.\n" +
                "First, based on the data, choose the appropriate keywords from the voiceTone array.\n" +
                "```\n" +
                "const voiceTone = [\n" +
                "  'Soothing',\n" +
                "  'Energetic',\n" +
                "  'Professional',\n" +
                "  'Bold',\n" +
                "  'Sophisticated',\n" +
                "  'Trendy',\n" +
                "  'Welcoming',\n" +
                "  'Classic',\n" +
                "  'Adaptable',\n" +
                "  'Minimalist',\n" +
                "  'Elegant',\n" +
                "  'Playful',\n" +
                "  'Calming',\n" +
                "  'Architectural',\n" +
                "  'Confident',\n" +
                "  'Artistic',\n" +
                "  'Chic',\n" +
                "  'Friendly',\n" +
                "]\n" +
                "```\n" +
                "\n" +
                "Next, extract choose the appropriate keywords from the spaceCondition array.\n" +
                "```\n" +
                "const spaceCondition = [\n" +
                "  'Hardwood Companion',\n" +
                "  'High Humidity Thrivers',\n" +
                "  'Low Light Tolerant',\n" +
                "  'Pet-Friendly',\n" +
                "  'Air-Purifying',\n" +
                "  'Drought-Resistant',\n" +
                "  'Small Spaces',\n" +
                "  'Large Spaces',\n" +
                "  'Minimalist Decor',\n" +
                "  'Tropical Vibes',\n" +
                "  'Desk Plants',\n" +
                "  'Hanging Varieties',\n" +
                "  'Feng Shui Plants',\n" +
                "  'Colorful Foliage',\n" +
                "  'Fragrant',\n" +
                "]\n" +
                "```\n" +
                "\n" +
                "Give me the result in the json format specified below.\n" +
                "\n" +
                "```json\n" +
                "{\n" +
                "\t\"voiceAndTones\": [ ],\n" +
                "\t\"spaceConditions\": [ ]\n" +
                "}\n" +
                "```"));
        messages.add(new Message(USER_ROLE, text));

        CreateThreadRequest request = new CreateThreadRequest(messages);

        String threadId = createThread(request);
        String runId = runThread(threadId);

        sleep5();
        int i = 1;
        while(!isRunComplete(threadId, runId)) {
            log.info("check status {}", i++);
            sleep5();
        }

        String message = getMessage(threadId);

        RecommendDto recommendDto = extractResult(message);

        log.info("Recommend: {}", recommendDto);
        return recommendDto;
    }

    private static void sleep5() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(openaiApiKey);
        httpHeaders.set("OpenAI-Beta", "assistants=v1");
        return httpHeaders;
    }

    private String createThread(CreateThreadRequest body) {
        HttpHeaders httpHeaders = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openai.com/v1/threads";
        HttpEntity<CreateThreadRequest> request = new HttpEntity<>(body, httpHeaders);
        ThreadDto threadDto = restTemplate.postForObject(url, request, ThreadDto.class);
        return threadDto.id();
    }

    private String runThread(String threadId) {
        HttpHeaders httpHeaders = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String runUrl = String.format(RUN_URL, threadId);
        RunRequest body = new RunRequest(assistantId, "");
        HttpEntity<RunRequest> request = new HttpEntity<>(body, httpHeaders);
        RunResponse response = restTemplate.postForObject(runUrl, request, RunResponse.class);
        return response.id();
    }

    private boolean isRunComplete(String threadId, String runId) {
        HttpHeaders httpHeaders = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        String runUrl = String.format(RUN_URL, threadId) + "/" + runId;
        ResponseEntity<RunResponse> response = restTemplate.exchange(runUrl, HttpMethod.GET, request, RunResponse.class);
        log.info("Run Status: {}", response.getBody().status());
        if (response.getBody().status().equals("failed")) {
            log.error("Fail {}", response.getBody());
            throw new RuntimeException("Analyze Failed");
        }
        return response.getBody().status().equals("completed");
    }

    private String uploadFiles() {
        final String url = "https://api.openai.com/v1/files";
        return null;
    }

    private String getMessage(String threadId) {
        HttpHeaders httpHeaders = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openai.com/v1/threads/" + threadId +"/messages?sort=desc";
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<MessageList> response = restTemplate.exchange(url, HttpMethod.GET, request, MessageList.class);
        log.info("Size: {}", response.getBody().data().size());
        log.info("Assistant Message: {}", response.getBody().data().get(0));

        return response.getBody().data().get(0).content().get(0).text().value();
    }

    private RecommendDto extractResult(String message) {
        final String start = "```json";
        final String end = "```";

        int startIndex = message.indexOf(start);
        int endIndex = message.lastIndexOf(end);
        String json = message.substring(startIndex + 7, endIndex);

        try {
            return objectMapper.readValue(json, RecommendDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
