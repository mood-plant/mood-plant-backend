package team.tourgini.moodplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tourgini.moodplant.entity.Result;
import team.tourgini.moodplant.entity.ResultPlant;

import java.util.List;

public interface ResultPlantRepository extends JpaRepository<ResultPlant, Long> {
    List<ResultPlant> findAllByResult(Result result);
}
