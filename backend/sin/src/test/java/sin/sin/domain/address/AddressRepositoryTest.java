package sin.sin.domain.address;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.JPARepositoryTest;
import sin.sin.domain.member.Member;
import sin.sin.handler.exception.NotExistsAddressException;

public class AddressRepositoryTest extends JPARepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    Member member;
    Address address1;
    Address address2;

    @BeforeEach
    void setup() {
        member = testEntityManager.find(Member.class, 1L);
        address1 = Address.builder()
            .address("주소")
            .member(member)
            .recipient("하하하핳")
            .recipientPhoneNumber("010-1111-1111")
            .original(true)
            .selected(true)
            .build();
        address2 = Address.builder()
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
    }

    @Test
    @Transactional
    void findAllByMemberId() {
        //when
        List<Address> addresses = addressRepository.findAllByMemberId(member.getId());

        //then
        assertThat(addresses.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void existsByIdAndMemberId() {
        //given
        Member other = testEntityManager.find(Member.class, 2L);
        Address address3 = Address.builder()
            .address("주소")
            .member(other)
            .original(false)
            .selected(false)
            .build();
        other.addAddress(address3);

        testEntityManager.persist(other);
        testEntityManager.flush();
        testEntityManager.clear();

        //when && then
        assertThat(addressRepository.existsByIdAndMemberId(
            address1.getId(), member.getId())).isEqualTo(true);
        assertThat(addressRepository.existsByIdAndMemberId(
            address1.getId(), other.getId())).isEqualTo(false);
        assertThat(addressRepository.existsByIdAndMemberId(
            address3.getId(), member.getId())).isEqualTo(false);
    }

    @Test
    @Transactional
    void findByOriginalTrue() {
        //when
        Address originalAddress = addressRepository.findByOriginalTrueAndMemberId(member.getId())
            .orElseThrow(() -> new NotExistsAddressException("해당되는 주소가 없습니다."));

        //then
        assertThat(originalAddress.getId()).isEqualTo(address1.getId());
    }

    @Test
    @Transactional
    void findBySelectedTrueAndMemberId() {
        //when
        Address selectedAddress = addressRepository.findBySelectedTrueAndMemberId(member.getId())
            .orElseThrow(() -> new NotExistsAddressException("해당되는 주소가 없습니다."));

        //then
        assertThat(selectedAddress.getId()).isEqualTo(address1.getId());
    }
}