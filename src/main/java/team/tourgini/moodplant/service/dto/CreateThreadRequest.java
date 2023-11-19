package team.tourgini.moodplant.service.dto;

import java.util.List;

public record CreateThreadRequest(
        List<Message> messages
) {
}
