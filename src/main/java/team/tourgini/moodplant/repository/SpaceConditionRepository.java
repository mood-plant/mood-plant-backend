package team.tourgini.moodplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tourgini.moodplant.domain.SpaceCondition;

import java.util.List;
import java.util.Optional;

public interface SpaceConditionRepository extends JpaRepository<SpaceCondition, Long> {

    Optional<SpaceCondition> findByValue(String value);

    List<SpaceCondition> findByValueIn(List<String> values);
}
