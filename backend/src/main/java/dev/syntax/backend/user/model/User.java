package dev.syntax.backend.user.model;

import dev.syntax.backend.subscribe.model.Subscribe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Setter
@Getter
//사용자 테이블
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(unique = true,nullable = false)
    private String id;

    private String password;

    @Column(nullable = false,length = 20)
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_age_group")
    private UserAgeGroup userAgeGroup;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_purpose")
    private ServicePurpose servicePurpose;

    @Column(name = "account_number")
    private String accountNumber; // 계좌 번호 입력

    @Column(name = "asset")
    private Long asset; // 자산

    @Column(name = "auth_number")
    private int authNumber; // 인증번호

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Subscribe> subscribes;

    //활동 가능 고객인지 여부
    @Column(name = "user_status", columnDefinition = "BOOLEAN DEFAULT true")
    private boolean userStatus;

    public void updateUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

}
