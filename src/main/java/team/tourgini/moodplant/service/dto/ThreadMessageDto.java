package team.tourgini.moodplant.service.dto;

import java.time.Instant;
import java.util.List;

public record ThreadMessageDto(
        String id,
        String object,
        Instant createdAt,
        String threadId,
        String role,
        List<Content> content,
        String[] fileIds,
        String assistantId,
        String runId,
        Metadata metadata
) {
    public record Content(
            String type,
            Text text
    ) {}

    public record Text(
            String value,
            Annotation[] annotations
    ) {}

    public record Annotation() {}

    public record Metadata() {}
}

