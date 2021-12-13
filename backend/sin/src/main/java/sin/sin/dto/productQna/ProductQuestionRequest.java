package sin.sin.dto.productQna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sin.sin.domain.productQuestion.Secret;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProductQuestionRequest {
    private String goodsNo;
    private String title;
    private String content;
    private Secret secret;
}
