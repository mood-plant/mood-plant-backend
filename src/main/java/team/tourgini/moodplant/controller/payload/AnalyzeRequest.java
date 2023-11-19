package team.tourgini.moodplant.controller.payload;

import jakarta.validation.constraints.NotBlank;

public record AnalyzeRequest(
        @NotBlank String link
){}
