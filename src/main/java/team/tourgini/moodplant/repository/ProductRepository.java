package team.tourgini.moodplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tourgini.moodplant.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
