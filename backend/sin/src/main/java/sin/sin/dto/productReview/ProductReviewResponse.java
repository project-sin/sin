package sin.sin.dto.productReview;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString
public class ProductReviewResponse {
    Long id;
    String title;
    String memberName;
    Timestamp createdDate;
    int likeCnt;
    int views;
    String productName;
    List<String> productImg = new ArrayList<>();
    String content;
}
