package sin.sin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sin.sin.domain.event.SearchEventRepository;
import sin.sin.domain.product.SearchMainRepository;
import sin.sin.dto.EventResponse;
import sin.sin.dto.MainResponse;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MainService {

    private final SearchEventRepository searchEventRepository;
    private final SearchMainRepository searchMainRepository;

    @Transactional
    public List<EventResponse> findMainBanner(){
        return searchEventRepository.findTop3ByOrderByCreatedDateDesc();
    }

    public List<MainResponse> findRecommendProduct(){
        return searchMainRepository.findTop8ByOrderByCreatedDate();
    }

    public List<MainResponse> findCheapProduct(){
        return searchMainRepository.findTop8ByOrderByDiscountPercentDesc();
    }

    public List<MainResponse> findMdChoice(){
        return searchMainRepository.findTop4ByOrderByProductReviewDesc();
    }
}
