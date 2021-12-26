package sin.sin.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.address.Address;
import sin.sin.domain.address.AddressRepository;
import sin.sin.domain.member.Member;
import sin.sin.domain.member.MemberRepository;
import sin.sin.dto.address.AddressRequest;
import sin.sin.dto.address.AddressResponse;
import sin.sin.dto.address.EditAddressRequest;
import sin.sin.handler.exception.NotExistsAddressException;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final MemberRepository memberRepository;

    private final AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public List<AddressResponse> findAddresses(Member member) {
        List<Address> addresses = addressRepository.findAllByMemberId(member.getId());

        return addresses.stream()
            .map((address) -> new AddressResponse(
                address.getId(),
                address.getAddress(),
                address.getRecipient(),
                address.getRecipientPhoneNumber(),
                address.isSelected(),
                address.isOriginal()
            ))
            .collect(Collectors.toList());
    }

    @Transactional
    public void addAddress(Member member, AddressRequest addressRequest) {
        if (addressRequest.isOriginal()) {
            Address originalAddress = addressRepository.findByOriginalTrueAndMemberId(
                member.getId()).orElseThrow(() -> new NotExistsAddressException("해당되는 주소가 없습니다."));
            originalAddress.setOriginal(false);
        }
        member.addAddress(Address.builder()
            .address(addressRequest.getAddress())
            .member(member)
            .original(addressRequest.isOriginal())
            .selected(false)
            .build());
        memberRepository.save(member);
    }

    @Transactional
    public void deleteAddress(Member member, Long addressId) {
        if (!addressRepository.existsByIdAndMemberId(addressId, member.getId())) {
            throw new NotExistsAddressException("회원께서 가지고계신 주소가 아닙니다");
        }
        addressRepository.deleteById(addressId);
    }

    @Transactional
    public void editAddress(Member member, EditAddressRequest request) {
        Address address = addressRepository.findByIdAndMemberId(request.getAddressId(),
            member.getId()).orElseThrow(() -> new NotExistsAddressException("해당되는 주소가 없습니다."));

        // , 를 기준으로 뒷부분이 상세주소, 마켓컬리는 상세주소만 수정 가능
        if (Objects.nonNull(request.getAddress()) && !request.getAddress().isEmpty()) {
            address.setAddress(address.getAddress().split(",")[0] + "," + request.getAddress());
        }
        if (Objects.nonNull(request.getRecipient()) && !request.getRecipient().isEmpty()) {
            address.setRecipient(request.getRecipient());
        }
        if (Objects.nonNull(request.getRecipientPhoneNumber()) &&
            !request.getRecipientPhoneNumber().isEmpty()) {
            address.setRecipientPhoneNumber(request.getRecipientPhoneNumber());
        }
        // 기본 주소 설정을 하면 true 를 입력받을 예정
        if (Objects.nonNull(request.isOriginal()) && request.isOriginal()) {
            Address originalAddress = addressRepository.findByOriginalTrueAndMemberId(
                member.getId()).orElseThrow(() -> new NotExistsAddressException("해당되는 주소가 없습니다."));
            originalAddress.setOriginal(false);
            address.setOriginal(true);
        }
    }

    @Transactional
    public void changeSelectedAddress(Member member, Long addressId) {
        Address changeAddress = addressRepository.findByIdAndMemberId(addressId,
            member.getId()).orElseThrow(() -> new NotExistsAddressException("해당되는 주소가 없습니다."));
        Address selectedAddress = addressRepository.findBySelectedTrueAndMemberId(
            member.getId()).orElseThrow(() -> new NotExistsAddressException("해당되는 주소가 없습니다."));
        selectedAddress.setSelected(false);
        changeAddress.setSelected(true);
    }
}
