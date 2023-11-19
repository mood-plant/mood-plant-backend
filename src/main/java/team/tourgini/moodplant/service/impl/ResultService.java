package team.tourgini.moodplant.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.tourgini.moodplant.domain.Result;
import team.tourgini.moodplant.domain.ResultSpaceCondition;
import team.tourgini.moodplant.domain.ResultTheme;
import team.tourgini.moodplant.domain.ResultVoiceAndTone;
import team.tourgini.moodplant.domain.SpaceCondition;
import team.tourgini.moodplant.domain.Theme;
import team.tourgini.moodplant.domain.VoiceAndTone;
import team.tourgini.moodplant.repository.ResultRepository;
import team.tourgini.moodplant.repository.ResultSpaceConditionRepository;
import team.tourgini.moodplant.repository.ResultThemeRepository;
import team.tourgini.moodplant.repository.ResultVoiceAndToneRepository;
import team.tourgini.moodplant.repository.SpaceConditionRepository;
import team.tourgini.moodplant.repository.ThemeRepository;
import team.tourgini.moodplant.repository.VoiceAndToneRepository;
import team.tourgini.moodplant.service.dto.ResultDto;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;
    private final ResultThemeRepository resultThemeRepository;
    private final ResultVoiceAndToneRepository resultVoiceAndToneRepository;
    private final ResultSpaceConditionRepository resultSpaceConditionRepository;
    private final ThemeRepository themeRepository;
    private final VoiceAndToneRepository voiceAndToneRepository;
    private final SpaceConditionRepository spaceConditionRepository;

    @Transactional(readOnly = true)
    public ResultDto readResult(Long resultId) {
        Result result = resultRepository.findById(resultId)
                .orElseThrow(NoSuchElementException::new);
        List<String> themes = result.getThemes()
                .stream()
                .map(ResultTheme::getTheme)
                .map(Theme::getValue)
                .toList();

        List<String> voiceAndTones = result.getVoiceAndTones()
                .stream()
                .map(ResultVoiceAndTone::getVoiceAndTone)
                .map(VoiceAndTone::getValue)
                .toList();

        List<String> spaceCondition = result.getSpaceConditions()
                .stream()
                .map(ResultSpaceCondition::getSpaceCondition)
                .map(SpaceCondition::getValue)
                .toList();

        ResultDto resultDto = new ResultDto(themes, voiceAndTones, spaceCondition);
        log.info("Read Result: {}", resultDto);
        return resultDto;
    }

    @Transactional
    public Long createResult(
            List<Theme> themes,
            List<VoiceAndTone> voiceAndTones,
            List<SpaceCondition> spaceConditions
    ) {
        Result result = new Result();

        for (Theme theme : themes) {
            ResultTheme resultTheme = new ResultTheme(result, theme);
            resultThemeRepository.save(resultTheme);
        }

        for (VoiceAndTone voiceAndTone : voiceAndTones) {
            ResultVoiceAndTone resultVoiceAndTone = new ResultVoiceAndTone(result, voiceAndTone);
            resultVoiceAndToneRepository.save(resultVoiceAndTone);
        }

        for (SpaceCondition spaceCondition : spaceConditions) {
            ResultSpaceCondition resultSpaceCondition = new ResultSpaceCondition(result, spaceCondition);
            resultSpaceConditionRepository.save(resultSpaceCondition);
        }

        Result savedResult = resultRepository.save(result);

        log.info("Created Result Id: {}", savedResult.getId());

        return savedResult.getId();
    }

    @Transactional
    public Long updateResult(Long resultId, ResultDto resultDto) {
        // ToDo delete create가 아닌 수정으로 변경
        // deleteResult(resultId);

        List<Theme> themes = new ArrayList<>();
        List<VoiceAndTone> voicedAndTones = new ArrayList<>();
        List<SpaceCondition> spacedConditions = new ArrayList<>();

        for (String theme : resultDto.themes()) {
            themeRepository.findByValue(theme)
                    .ifPresent(themes::add);
        }

        for (String voiceAndTone : resultDto.voiceAndTones()) {
            voiceAndToneRepository.findByValue(voiceAndTone)
                    .ifPresent(voicedAndTones::add);
        }

        for (String spaceCondition : resultDto.spaceConditions()) {
            spaceConditionRepository.findByValue(spaceCondition)
                    .ifPresent(spacedConditions::add);
        }

        log.info("Modify themes: {}", themes);
        log.info("Modify voicedAndTones: {}", voicedAndTones);
        log.info("Modify spacedConditions: {}", spacedConditions);

        return createResult(themes, voicedAndTones, spacedConditions);
    }

    @Transactional
    public void deleteResult(Long resultId) {
        Result resultForDelete = resultRepository.findById(resultId)
                .orElseThrow(NoSuchElementException::new);
        resultRepository.delete(resultForDelete);
    }
}
