package sin.sin.dto.ProductDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ProductQnaResponse {
    ProductQuestionResponse productQuestionResponse;
    ProductQuestionReplyResponse productQuestionReply;
}
