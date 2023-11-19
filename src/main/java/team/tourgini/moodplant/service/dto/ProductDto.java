package team.tourgini.moodplant.service.dto;

public record ProductDto(
        Long id,
        String name,
        String description,
        String image,
        Integer price
) {
}
