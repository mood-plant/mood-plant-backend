package team.tourgini.moodplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tourgini.moodplant.domain.PlantSpaceCondition;

public interface GuideSpaceConditionRepository extends JpaRepository<PlantSpaceCondition, Long> {
}
