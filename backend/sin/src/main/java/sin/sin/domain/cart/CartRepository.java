package sin.sin.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import sin.sin.domain.member.Member;
import sin.sin.domain.product.Product;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByMemberAndProduct(Member member, Product product);
}