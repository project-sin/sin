package sin.sin.domain.privateQuestionImg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sin.sin.domain.privateQuestion.PrivateQuestion;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PrivateQuestionImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="private_question_img_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "private_question_id")
    private PrivateQuestion privateQuestion;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;
}
