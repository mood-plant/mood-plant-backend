package team.tourgini.moodplant.service.dto;

import java.time.Instant;

public record RunResponse(
        String id,
        String object,
        Instant createdAt,
        String assistantId,
        String threadId,
        String status,
        Instant startedAt,
        Instant expiresAt,
        Instant cancelledAt,
        Instant failedAt,
        Instant completedAt,
        String lastError,
        String model,
        String instructions,
        Tool[] tools,
        String[] fileIds,
        Metadata metadata
) {
    public record Tool(
            String type
    ) {}

    public record Metadata() {}
}
