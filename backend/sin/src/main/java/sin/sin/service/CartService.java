package sin.sin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.cart.Cart;
import sin.sin.domain.cart.CartRepository;
import sin.sin.domain.cart.SearchCartRepository;
import sin.sin.domain.member.Member;
import sin.sin.domain.member.MemberRepository;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRepository;
import sin.sin.dto.ProductFrame;
import sin.sin.dto.cart.CartRequest;
import sin.sin.dto.cart.MemberCartResponse;
import sin.sin.handler.exception.NoProductException;
import sin.sin.handler.exception.RequestLoginException;
import sin.sin.util.ImgUtil;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final SearchCartRepository searchCartRepository;
    private final ProductRepository productRepository;
    private final ImgUtil imgUtil;

    @Transactional
    public void add(Long id, CartRequest cartRequest) {
        Member member = existMember(id);
        Product product = existProduct(cartRequest.getProductCode());

        Optional<Cart> ExistCart = cartRepository.findByMemberAndProduct(member, product);
        int cnt = cartRequest.getCnt();

        if (ExistCart.isEmpty()) {
            Cart newCart = Cart.builder()
                    .member(member)
                    .product(product)
                    .count(cnt)
                    .build();
            cartRepository.save(newCart);
        } else {
            ExistCart.get().setCount(cnt);
        }
    }

    @Transactional
    public void delete(Long id, List<String> productCodeList) {
        Member member = existMember(id);

        for (String productCode : productCodeList) {
            Product product = existProduct(productCode);
            Optional<Cart> ExistCart = cartRepository.findByMemberAndProduct(member, product);
            cartRepository.delete(ExistCart.get());
        }
    }

    @Transactional(readOnly = true)
    public List<MemberCartResponse> selectMemberCart(Long id) {

        List<MemberCartResponse> cartRepositoryResponseList = searchCartRepository.findProductDetailByMemberId(id);
        imgUtil.updateImgUrlProducts(cartRepositoryResponseList);

        return cartRepositoryResponseList;
    }

    @Transactional(readOnly = true)
    public List<ProductFrame> findProductDetailByProductCode(List<String> productCodeList) {

        List<ProductFrame> cartRepositoryResponseList = searchCartRepository.findProductDetailByProductCode(productCodeList);
        imgUtil.updateImgUrlProducts(cartRepositoryResponseList);

        return cartRepositoryResponseList;
    }

    private Member existMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() ->
                new RequestLoginException("로그인이 필요한 작업입니다.")
        );

        return member;
    }

    private Product existProduct(String productCode) {
        Product product = productRepository.findProductByProductCode(productCode).orElseThrow(() ->
                new NoProductException("해당 productCode에 일치하는 상품이 없습니다.")
        );

        return product;
    }
}
