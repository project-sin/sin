package sin.sin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.sin.domain.event.Classification;
import sin.sin.domain.product.Product;
import sin.sin.dto.EventResponse;
import sin.sin.dto.MainResponse;

import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MainServiceTest {
    @Autowired
    private MainService mainService;

    @Test
    void 메인배너() {
        //given
        List<EventResponse> mainBanner = mainService.findMainBanner();

        //when
        Classification classification = mainBanner.get(0).getClassification();

        //then
        assertThat(classification).isEqualTo(Classification.NonProducts);
    }

    @Test
    void 이상품_어때요() throws Exception {
        //given
        List<MainResponse> products = mainService.findRecommendProduct();

        //when
        int discountPercent = products.get(0).getDiscountPercent();

        //then
        assertThat(discountPercent).isEqualTo(44);
    }

    @Test
    void 놓치면_후회할_가격() throws Exception {
        //given
        List<MainResponse> products = mainService.findCheapProduct();

        //when
        int discountPercent = products.get(0).getDiscountPercent();

        //then
        assertThat(discountPercent).isEqualTo(44);

    }

    @Test
    void MD의_추천() throws Exception {
        //given
        HashMap<String, List<MainResponse>> mdChoice = mainService.findMdChoice();

        //when
        List<MainResponse> mainResponse = mdChoice.get("907");

        //then
        assertThat(mainResponse.size()).isEqualTo(4);

    }

}