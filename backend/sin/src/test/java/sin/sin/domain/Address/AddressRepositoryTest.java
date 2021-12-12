package sin.sin.domain.Address;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.JPARepositoryTest;
import sin.sin.domain.address.Address;
import sin.sin.domain.address.AddressRepository;
import sin.sin.domain.member.Member;

public class AddressRepositoryTest extends JPARepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @Transactional
    void findAllByMemberId(){
        //given
        Member member = testEntityManager.find(Member.class, 1L);
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
        member.addAddress(address1);
        member.addAddress(address2);

        testEntityManager.persist(member);
        testEntityManager.flush();
        testEntityManager.clear();

        //when
        List<Address> addresses = addressRepository.findAllByMemberId(member.getId());

        //then
        assertThat(addresses.size()).isEqualTo(2);
    }
}
