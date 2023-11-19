package team.tourgini.moodplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tourgini.moodplant.domain.PlantVoiceAndTone;

public interface GuideVoiceAndToneRepository extends JpaRepository<PlantVoiceAndTone, Long> {
}
