package team.tourgini.moodplant.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tourgini.moodplant.controller.payload.GuideResponse;
import team.tourgini.moodplant.service.dto.PlantDto;
import team.tourgini.moodplant.service.dto.ProductDto;
import team.tourgini.moodplant.service.impl.GuideService;

import java.util.List;

@Slf4j
@RequestMapping("/api/results/{id}/guides")
@RestController
@RequiredArgsConstructor
public class GuideController {

    private final GuideService guideService;

    @GetMapping
    public ResponseEntity<GuideResponse> listGuides(@PathVariable Long id) {
        List<PlantDto> plantDtos = guideService.recommendPlants(id);
        List<ProductDto> productDtos = guideService.recommendProducts(id);

        GuideResponse response = new GuideResponse(plantDtos, productDtos);
        return ResponseEntity.ok(response);
    }
}
