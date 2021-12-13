package sin.sin.dto.ProductDetails;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import sin.sin.domain.productQuestion.Secret;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
public class ProductQuestionResponse {
    Long id;
    String title;
    String content;
    String name;
    Secret secret;
    Timestamp createdDate;

    @QueryProjection
    public ProductQuestionResponse(Long id, String title, String content, String name, Secret secret, Timestamp createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.secret = secret;
        this.createdDate = createdDate;
    }
}
