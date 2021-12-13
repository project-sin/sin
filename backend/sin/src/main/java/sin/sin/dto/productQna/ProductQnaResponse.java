package sin.sin.dto.productQna;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Builder
@Getter
@ToString
public class ProductQnaResponse {
    ProductQuestionResponse productQuestionResponse;
    ProductQuestionReplyResponse productQuestionReply;

    @QueryProjection
    public ProductQnaResponse(ProductQuestionResponse productQuestionResponse, ProductQuestionReplyResponse productQuestionReply) {
        this.productQuestionResponse = productQuestionResponse;
        this.productQuestionReply = productQuestionReply;
    }
}
