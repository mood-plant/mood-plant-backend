package team.tourgini.moodplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tourgini.moodplant.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
