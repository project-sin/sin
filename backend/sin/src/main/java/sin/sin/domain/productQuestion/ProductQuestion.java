package sin.sin.domain.productQuestion;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.member.Member;
import sin.sin.domain.product.Product;
import sin.sin.domain.productQuestionReply.ProductQuestionReply;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"product"})
@Builder
@Getter
public class ProductQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_question_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    private Timestamp createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @OneToOne(mappedBy = "productQuestion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProductQuestionReply productQuestionReply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @NotNull
    @Enumerated(EnumType.STRING) // 이넘 이름을 DB에 저장
    private Secret secret;
}
