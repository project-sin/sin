package sin.sin.domain.member;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.level.Level;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(name = "login_id", nullable = false)
    private String _id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @NotNull
    @Enumerated(EnumType.STRING) // 이넘 이름을 DB에 저장
    private Gender gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String birth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "level_id")
    private Level level;

    @NotNull
    @Enumerated(EnumType.STRING) // 이넘 이름을 DB에 저장
    private Role role;

    @CreationTimestamp
    private Timestamp createdDate;

    @Column(nullable = false)
    private boolean privateInfo;

    @Column(nullable = false)
    private boolean adSms;

    @Column(nullable = false)
    private boolean adEmail;

    public void updatePassword(String password){
        this.password = password;
    }

    public void updateName(String name){
        this.name = name;
    }

    public void updateEmail(String email){
        this.email = email;
    }

    public void updatePhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void updateGender(Gender gender){
        this.gender = gender;
    }

    public void updateBirth(String birth){
        this.birth = birth;
    }
}
