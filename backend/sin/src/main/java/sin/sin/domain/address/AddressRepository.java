package sin.sin.domain.address;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Boolean existsByIdAndMemberId(Long addressId, Long MemberId);

    List<Address> findAllByMemberId(Long id);
}
