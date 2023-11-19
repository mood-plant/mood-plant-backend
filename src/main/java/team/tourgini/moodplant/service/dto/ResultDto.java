package team.tourgini.moodplant.service.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ResultDto(
        @NotBlank List<String> themes,
        @NotBlank List<String> voiceAndTones,
        @NotBlank List<String> spaceConditions
) {
}
