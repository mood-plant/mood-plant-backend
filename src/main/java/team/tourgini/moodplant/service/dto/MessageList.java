package team.tourgini.moodplant.service.dto;

import java.util.List;

public record MessageList(
        String object,
        List<ThreadMessageDto> data
) {
}
