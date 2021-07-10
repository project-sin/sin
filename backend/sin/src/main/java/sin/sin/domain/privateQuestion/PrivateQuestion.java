package sin.sin.domain.privateQuestion;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.member.Member;
import sin.sin.domain.orders.Orders;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PrivateQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="private_question_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "member_id")
    private  Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String categoryName;

    @CreationTimestamp
    private Timestamp createdDate;

    @Column(nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(nullable = false, name = "orders_id")
    private Orders orders;

    @Column(nullable = false)
    private String phoneNumber;

    @Lob
    @Column(nullable = false)
    private String content;
}
