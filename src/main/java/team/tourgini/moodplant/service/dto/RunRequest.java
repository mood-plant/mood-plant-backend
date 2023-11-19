package team.tourgini.moodplant.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RunRequest(
        @JsonProperty("assistant_id") String assistantId,
        String instructions
) {
}
