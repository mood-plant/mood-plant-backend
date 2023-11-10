package team.tourgini.moodplant.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scraper {

    public Collection<String> extractText(String uri) {
        Set<String> texts = new HashSet<>();

        Document document = null;
        try {
            document = Jsoup.connect(uri)
                    .get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Title: {}, URI: {}", document.title(), uri);

        Elements content = document.select("div[id^=main], div[class^=main]");
        for (Element element : content) {
            log.info("Element: {}", element.text());
            texts.add(element.text());
        }

        return texts;
    }
}
