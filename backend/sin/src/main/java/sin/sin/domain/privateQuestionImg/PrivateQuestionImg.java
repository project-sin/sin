package sin.sin.domain.privateQuestionImg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PrivateQuestionImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="private_question_img_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;
}
