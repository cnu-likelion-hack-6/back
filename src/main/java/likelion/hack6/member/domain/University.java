package likelion.hack6.member.domain;

import java.util.Arrays;
import likelion.hack6.common.exception.type.BadRequestException;
import lombok.Getter;

@Getter
public enum University {

    CHUNGNAM_NATIONAL_UNIVERSITY("충남대학교", "cnu.ac.kr");

    private final String universityName;
    private final String emailDomain;

    University(String universityName, String emailDomain) {
        this.universityName = universityName;
        this.emailDomain = emailDomain;
    }

    public static University fromEmail(String email) {
        String domain = email.split("@")[1];
        return Arrays.stream(University.values())
                .filter(it -> domain.equals(it.emailDomain))
                .findAny()
                .orElseThrow(() -> new BadRequestException("지원되지 않는 학교 이메일입니다."));
    }
}
