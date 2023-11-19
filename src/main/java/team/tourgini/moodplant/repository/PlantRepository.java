package team.tourgini.moodplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tourgini.moodplant.domain.Plant;

import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    Optional<Plant> findByName(String name);
}
