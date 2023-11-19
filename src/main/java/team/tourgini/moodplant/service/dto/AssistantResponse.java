package team.tourgini.moodplant.service.dto;

import java.util.List;


public record AssistantResponse(
        List<Choice> choices
) {
}
