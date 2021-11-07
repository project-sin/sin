package sin.sin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.cart.Cart;
import sin.sin.domain.cart.CartRepository;
import sin.sin.domain.member.Member;
import sin.sin.domain.member.MemberRepository;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRepository;
import sin.sin.handler.exception.NoProductException;
import sin.sin.handler.exception.RequestLoginException;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void add(Long id, String productCode){
        Member member = memberRepository.findById(id).orElseThrow(() ->
                new RequestLoginException("로그인이 필요한 작업입니다.")
        );

        Product product = productRepository.findProductByProductCode(productCode).orElseThrow(()->
                new NoProductException("해당 productCode에 일치하는 상품이 없습니다.")
        );

        Optional<Cart> ExistCart = cartRepository.findByMemberAndProduct(member, product);

        if(ExistCart.isEmpty()){
            Cart newCart = Cart.builder()
                    .member(member)
                    .product(product)
                    .count(1)
                    .build();
            cartRepository.save(newCart);
        } else{
            ExistCart.get().add();
        }


    }
}
