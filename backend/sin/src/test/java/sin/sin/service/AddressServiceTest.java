package sin.sin.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sin.sin.domain.address.Address;
import sin.sin.domain.address.AddressRepository;
import sin.sin.domain.member.Gender;
import sin.sin.domain.member.Member;
import sin.sin.dto.AddressResponse;

@ExtendWith(MockitoExtension.class)
@Transactional
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @Test
    void findAddresses(){
        //given
        Member member = Member.builder()
            ._id("test")
            .password("password")
            .name("하하ㅏㅎㅎ")
            .email("email")
            .phoneNumber("010-1111-1111")
            .gender(Gender.Male)
            .birth("1999-99-99")
            .build();

        Address address1 = Address.builder()
            .address("주소")
            .member(member)
            .recipient("하하하핳")
            .recipientPhoneNumber("010-1111-1111")
            .original(true)
            .selected(true)
            .build();
        Address address2 = Address.builder()
            .address("주소")
            .member(member)
            .original(false)
            .selected(false)
            .build();

        List<Address> addresses = new ArrayList<>();
        addresses.add(address1);
        addresses.add(address2);

        given(addressRepository.findAllByMemberId(member.getId())).willReturn(addresses);

        //when
        List<AddressResponse> responses = addressService.findAddresses(member);

        //then
        assertThat(responses.size()).isEqualTo(2);
        System.out.println(responses);
    }
}
