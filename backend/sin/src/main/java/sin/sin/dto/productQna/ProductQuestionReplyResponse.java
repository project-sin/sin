package sin.sin.dto.productQna;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
public class ProductQuestionReplyResponse {
    Long id;
    String content;
    Timestamp createdDate;

    @QueryProjection
    public ProductQuestionReplyResponse(Long id, String content, Timestamp createdDate) {
        this.id=id;
        this.content = content;
        this.createdDate = createdDate;
    }
}
