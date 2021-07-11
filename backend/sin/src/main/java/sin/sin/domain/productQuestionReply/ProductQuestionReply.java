package sin.sin.domain.productQuestionReply;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.productQuestion.ProductQuestion;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductQuestionReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_question_reply_id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false, name = "product_question_id")
    private ProductQuestion productQuestion;

    @Lob
    @Column(nullable = false)
    private String Content;

    @CreationTimestamp
    private Timestamp createdDate;
}
