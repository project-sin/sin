package sin.sin.domain.level;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Long> {
    
    Level findFirstByOrderBySalePercentAsc();
}