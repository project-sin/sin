package sin.sin.domain.address;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByMemberId(Long id);
}
