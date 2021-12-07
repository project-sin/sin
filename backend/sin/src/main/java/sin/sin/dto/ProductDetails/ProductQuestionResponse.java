package sin.sin.dto.ProductDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import sin.sin.domain.productQuestion.Secret;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
public class ProductQuestionResponse {
    String title;
    String content;
    String name;
    Secret secret;
    Timestamp createdDate;
}
