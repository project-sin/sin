package sin.sin.domain.member;

import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import sin.sin.domain.address.Address;
import sin.sin.domain.level.Level;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

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

    @Builder
    public Member(Long id, String _id, String password, String name, String email,
        String phoneNumber, Gender gender, List<Address> addresses, String birth,
        Level level, Role role, Timestamp createdDate, boolean privateInfo, boolean adSms,
        boolean adEmail) {
        this.id = id;
        this._id = _id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        if (Objects.nonNull(addresses)) {
            this.addresses = addresses;
        }
        this.birth = birth;
        this.level = level;
        this.role = role;
        this.createdDate = createdDate;
        this.privateInfo = privateInfo;
        this.adSms = adSms;
        this.adEmail = adEmail;
    }

    public void addAddress(Address address){
        this.addresses.add(address);
    }
}
