package sin.sin.dto.ProductDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
public class ProductQuestionReplyResponse {
    String content;
    Timestamp createdDate;
}
