package team.tourgini.moodplant.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;

public record FileDto(
        String id,
        String object,
        File bytes,
        @JsonProperty("created_at") String createdAt,
        String filename,
        String purpose
) {
}
