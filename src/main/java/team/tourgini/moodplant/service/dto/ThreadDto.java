package team.tourgini.moodplant.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ThreadDto(
        String id,
        String object,
        @JsonProperty("created_at") Integer createdAt,
        Object metadata
) {
}
