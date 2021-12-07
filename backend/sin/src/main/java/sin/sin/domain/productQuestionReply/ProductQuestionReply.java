package sin.sin.domain.productQuestionReply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.productQuestion.ProductQuestion;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "productQuestion")
@Getter
public class ProductQuestionReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_question_reply_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "product_question_id")
    private ProductQuestion productQuestion;

    @Lob
    @Column(nullable = false)
    private String Content;

    @CreationTimestamp
    private Timestamp createdDate;
}
