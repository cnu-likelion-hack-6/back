package likelion.hack6.member.domain;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.Duration;
import java.time.LocalDateTime;
import likelion.hack6.common.exception.type.BadRequestException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor(access = PROTECTED)
@Embeddable
public class UniversityEmail {

    private String email;

    private String certificatedCode;

    private LocalDateTime certificationCodeSendDateTime;

    private boolean isCertificated;

    @Enumerated(EnumType.STRING)
    private University university;

    public UniversityEmail(
            String email,
            String emailCertificatedCode
    ) {
        if (email != null) {
            this.email = email;
            this.certificatedCode = emailCertificatedCode;
            this.certificationCodeSendDateTime = LocalDateTime.now();
            this.university = University.fromEmail(email);
        }
    }

    public void certificate(String code) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(certificationCodeSendDateTime, now);
        long minutes = duration.toMinutes() % 60;
        if (minutes > 10) {
            log.info("이메일 확인코드 만료로 인한 인증 실패. 발행일 = {}, 현 시간 = {}", certificationCodeSendDateTime, now);
            throw new BadRequestException("이메일 확인코드가 만료되었습니다. 재인증 해주세요");
        }
        if (!certificatedCode.equals(code)) {
            log.info("이메일 확인코드 불일치로 인한 인증 실패. actual = {}, input = {}", certificatedCode, code);
            throw new BadRequestException("이메일 확인코드가 일치하지 않습니다.");
        }
        this.isCertificated = true;
        this.certificatedCode = null;
        this.certificationCodeSendDateTime = null;
    }
}
