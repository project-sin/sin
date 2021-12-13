package sin.sin.domain.address;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByOriginalTrueAndMemberId(Long memberId);

    Optional<Address> findByIdAndMemberId(Long addressId, Long memberId);

    Boolean existsByIdAndMemberId(Long addressId, Long memberId);

    List<Address> findAllByMemberId(Long id);
}
