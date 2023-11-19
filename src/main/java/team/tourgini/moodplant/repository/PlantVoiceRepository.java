package team.tourgini.moodplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tourgini.moodplant.domain.PlantVoiceAndTone;

public interface PlantVoiceRepository extends JpaRepository<PlantVoiceAndTone, Long> {
}
