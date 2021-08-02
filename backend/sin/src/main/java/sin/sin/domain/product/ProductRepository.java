package sin.sin.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCreatedDateGreaterThanEqual(Timestamp thisMonth);
}
