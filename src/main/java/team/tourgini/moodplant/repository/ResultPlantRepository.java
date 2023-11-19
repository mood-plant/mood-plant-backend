package team.tourgini.moodplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tourgini.moodplant.domain.Result;
import team.tourgini.moodplant.domain.ResultPlant;

import java.util.List;
import java.util.Optional;

public interface ResultPlantRepository extends JpaRepository<ResultPlant, Long> {
    List<ResultPlant> findAllByResult(Result result);
}
