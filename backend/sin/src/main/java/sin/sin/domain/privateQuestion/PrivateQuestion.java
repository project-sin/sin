package sin.sin.domain.privateQuestion;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.member.Member;
import sin.sin.domain.orders.Orders;
import sin.sin.domain.privateQuestionImg.PrivateQuestionImg;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PrivateQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="private_question_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    private  Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @CreationTimestamp
    private Timestamp createdDate;

    @Column(nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "orders_id")
    private Orders orders;

    @Column(nullable = false)
    private String phoneNumber;

    @Lob
    @Column(nullable = false)
    private String content;

    @OneToMany
    @JoinColumn(name= "private_question_img_id")
    private List<PrivateQuestionImg> privateQuestionImgList = new ArrayList<>();
}
