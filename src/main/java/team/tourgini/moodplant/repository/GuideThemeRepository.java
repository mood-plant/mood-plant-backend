package team.tourgini.moodplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tourgini.moodplant.domain.PlantTheme;

public interface GuideThemeRepository extends JpaRepository<PlantTheme, Long> {
}
