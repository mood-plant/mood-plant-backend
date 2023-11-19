package team.tourgini.moodplant.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.tourgini.moodplant.domain.Plant;
import team.tourgini.moodplant.domain.PlantSpaceCondition;
import team.tourgini.moodplant.domain.PlantTheme;
import team.tourgini.moodplant.domain.PlantVoiceAndTone;
import team.tourgini.moodplant.domain.Result;
import team.tourgini.moodplant.domain.ResultPlant;
import team.tourgini.moodplant.domain.ResultSpaceCondition;
import team.tourgini.moodplant.domain.ResultTheme;
import team.tourgini.moodplant.domain.ResultVoiceAndTone;
import team.tourgini.moodplant.repository.ResultPlantRepository;
import team.tourgini.moodplant.repository.ResultRepository;
import team.tourgini.moodplant.service.dto.PlantDto;
import team.tourgini.moodplant.service.dto.ProductDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuideService {

    private final ResultRepository resultRepository;
    private final ResultPlantRepository resultPlantRepository;

    @Transactional
    public List<PlantDto> recommendPlants(Long resultId) {
        Result result = resultRepository.findById(resultId)
                .orElseThrow(NoSuchElementException::new);

        Stream<Plant> plant1 = result.getThemes()
                .stream()
                .map(ResultTheme::getTheme)
                .flatMap((th) -> th.getPlantThemes().stream())
                .map(PlantTheme::getPlant)
                .distinct();

        Stream<Plant> plant2 = result.getVoiceAndTones()
                .stream()
                .map(ResultVoiceAndTone::getVoiceAndTone)
                .flatMap((vt) -> vt.getPlantVoiceAndTones().stream())
                .map(PlantVoiceAndTone::getPlant)
                .distinct();

        Stream<Plant> plant3 = result.getSpaceConditions()
                .stream()
                .map(ResultSpaceCondition::getSpaceCondition)
                .flatMap((sc) -> sc.getPlantSpaceConditions().stream())
                .map(PlantSpaceCondition::getPlant)
                .distinct();

        Stream<Plant> concated = Stream.concat(plant1, plant2);
        Stream.concat(concated, plant3)
                .forEach((plant) -> {
                    ResultPlant resultPlant = new ResultPlant(result, plant);
                    resultPlantRepository.save(resultPlant);
                });

        return resultPlantRepository.findAll()
                .stream()
                .filter(resultPlant -> resultPlant.getResult().equals(result))
                .distinct()
                .map(ResultPlant::getPlant)
                .map(plant -> new PlantDto(plant.getId(), plant.getName(), plant.getDescription(), plant.getImage(), plant.getPrice()))
                .collect(Collectors.toList());
    }

    public List<ProductDto> recommendProducts(Long resultId) {
        return null;
    }
}
