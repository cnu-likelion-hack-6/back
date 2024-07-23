package likelion.hack6.member.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import likelion.hack6.common.domain.RootEntity;
import likelion.hack6.common.exception.type.UnAuthorizedException;
import likelion.hack6.common.security.Sha256;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Member extends RootEntity<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = true, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Embedded
    private Profile profile;

    public Member(String phone, String password) {
        this.phone = phone;
        this.password = Sha256.encrypt(password);
        this.profile = Profile.unsetting();
    }

    public void signup(MemberValidator validator) {
        validator.validateDuplicatedPhone(this.phone);
    }

    public void login(String plainPassword) {
        if (!password.equals(Sha256.encrypt(plainPassword))) {
            log.info("비밀번호가 불일치로 인한 로그인 실패");
            throw new UnAuthorizedException("Invalid password");
        }
    }

    public void sendEmailCertificationCode(String email, String code) {
        profile.sendEmailCertificationCode(email, code);
    }

    public void certificateEmail(String code) {
        profile.certificateEmail(code);
    }

    public void setKakaoId(String kakaoId) {
        profile.setKakaoId(kakaoId);
    }

    public void setupProfile(Profile profile) {
        this.profile = profile;
    }
}
