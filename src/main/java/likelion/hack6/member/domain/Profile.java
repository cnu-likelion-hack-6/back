package likelion.hack6.member.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import java.util.Collections;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Profile {

    private String kakaoId;

    @Embedded
    private UniversityEmail universityEmail;

    private String name;  // 이름

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String major;  // 전공
    private Integer classOf;  // 학번 (ex: 19학번)

    @Enumerated(EnumType.STRING)
    private StudentStatus studentStatus;  // 상태

    private Integer grade;  // 학년
    private Integer age;  // 나이

    @Enumerated(EnumType.STRING)
    private ProfileIcon profileIcon;

    private String brief;  // 한 줄 소개

    @ElementCollection
    @CollectionTable(name = "INTEREST_KEYWORD", joinColumns = @JoinColumn(name = "member_id"))
    private Set<Keyword> keywords;  //  관심 키워드
    private boolean isSetup;

    public static Profile unsetting() {
        return new Profile(
                null,
                new UniversityEmail(null, null),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                Collections.emptySet(),
                false
        );
    }

    public boolean isEmailCertificated() {
        return universityEmail.isCertificated();
    }

    public void sendEmailCertificationCode(String email, String code) {
        this.universityEmail = new UniversityEmail(email, code);
    }

    public void certificateEmail(String code) {
        universityEmail.certificate(code);
    }

    public void setKakaoId(String kakaoId) {
        this.kakaoId = kakaoId;
    }
}
