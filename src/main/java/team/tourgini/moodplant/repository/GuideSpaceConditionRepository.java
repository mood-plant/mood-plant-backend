package team.tourgini.moodplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tourgini.moodplant.entity.PlantSpaceCondition;

public interface GuideSpaceConditionRepository extends JpaRepository<PlantSpaceCondition, Long> {
}
