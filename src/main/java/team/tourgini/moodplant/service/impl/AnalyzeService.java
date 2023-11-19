package team.tourgini.moodplant.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.tourgini.moodplant.domain.SpaceCondition;
import team.tourgini.moodplant.domain.Theme;
import team.tourgini.moodplant.domain.VoiceAndTone;
import team.tourgini.moodplant.repository.SpaceConditionRepository;
import team.tourgini.moodplant.repository.ThemeRepository;
import team.tourgini.moodplant.repository.VoiceAndToneRepository;
import team.tourgini.moodplant.service.AnalyzeUseCase;
import team.tourgini.moodplant.service.Analyzer;
import team.tourgini.moodplant.service.dto.RecommendDto;

import java.util.Collections;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class AnalyzeService implements AnalyzeUseCase {

    private final Analyzer analyzer;
    private final ResultService resultService;
    private final VoiceAndToneRepository voiceAndToneRepository;
    private final SpaceConditionRepository spaceConditionRepository;

    @Transactional
    @Override
    public Long analyze(String link) {

        RecommendDto recommend = analyzer.analyze(link);

        List<Theme> themes = Collections.emptyList();
        List<VoiceAndTone> voiceAndTones = voiceAndToneRepository.findByValueIn(recommend.voiceAndTones());
        List<SpaceCondition> spaceConditions = spaceConditionRepository.findByValueIn(recommend.spaceConditions());

        return resultService.createResult(themes, voiceAndTones, spaceConditions);
    }
}
