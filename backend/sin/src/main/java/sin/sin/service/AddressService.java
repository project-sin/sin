package sin.sin.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.address.Address;
import sin.sin.domain.address.AddressRepository;
import sin.sin.domain.member.Member;
import sin.sin.dto.AddressResponse;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public List<AddressResponse> findAddresses(Member member) {
        List<Address> addresses = addressRepository.findAllByMemberId(member.getId());

        return addresses.stream()
            .map((address)-> new AddressResponse(
                address.getId(),
                address.getAddress(),
                address.getRecipient(),
                address.getRecipientPhoneNumber(),
                address.isSelected(),
                address.isOriginal()
                ))
            .collect(Collectors.toList());
    }
}
