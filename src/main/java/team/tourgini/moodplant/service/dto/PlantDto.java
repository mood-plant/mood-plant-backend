package team.tourgini.moodplant.service.dto;

public record PlantDto(
        Long id,
        String name,
        String description,
        String image,
        Integer price
) {
}
