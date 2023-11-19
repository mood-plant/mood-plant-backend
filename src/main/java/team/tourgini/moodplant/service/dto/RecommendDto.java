package team.tourgini.moodplant.service.dto;

import lombok.Data;

import java.util.List;

public record RecommendDto(
        List<String> voiceAndTones,
        List<String> spaceConditions
) {
}
