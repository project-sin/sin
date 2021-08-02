package sin.sin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.sin.domain.product.Product;
import sin.sin.domain.product.ProductRepository;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class ProductListService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> newProductList(){
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-01 00:00:00.000");
        String today = format.format(System.currentTimeMillis());
        Timestamp ts = Timestamp.valueOf(today);
        log.info("이번달" + ts);
        return productRepository.findByCreatedDateGreaterThanEqual(ts);
    }

//    public List<Product> BestProductList(){
//    }

//    public List<Product> CheapProductList(){
//
//    }

}
