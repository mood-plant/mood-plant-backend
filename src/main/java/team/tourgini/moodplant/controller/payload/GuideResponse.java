package team.tourgini.moodplant.controller.payload;

import team.tourgini.moodplant.service.dto.PlantDto;
import team.tourgini.moodplant.service.dto.ProductDto;

import java.util.List;

public record GuideResponse(
        List<PlantDto> guides,
        List<ProductDto> themeProducts
) {
}
