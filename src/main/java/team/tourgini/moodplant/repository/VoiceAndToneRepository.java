package team.tourgini.moodplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tourgini.moodplant.entity.VoiceAndTone;

import java.util.List;
import java.util.Optional;

public interface VoiceAndToneRepository extends JpaRepository<VoiceAndTone, Long> {

    Optional<VoiceAndTone> findByValue(String value);

    List<VoiceAndTone> findByValueIn(List<String> values);
}
