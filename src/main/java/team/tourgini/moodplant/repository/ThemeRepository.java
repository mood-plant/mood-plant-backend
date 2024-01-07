package team.tourgini.moodplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tourgini.moodplant.entity.Theme;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Optional<Theme> findByValue(String value);

    List<Theme> findByValueIn(List<String> values);
}
