package likelion.hack6.member.domain;

import java.util.Arrays;
import java.util.List;
import likelion.hack6.common.exception.type.BadRequestException;
import lombok.Getter;

@Getter
public enum University {

    SEOUL_NATIONAL_UNIVERSITY("서울대학교", "snu.ac.kr"),
    YONSEI_UNIVERSITY("연세대학교", "yonsei.ac.kr"),
    KOREA_UNIVERSITY("고려대학교", "korea.ac.kr"),
    SOGANG_UNIVERSITY("서강대학교", "sogang.ac.kr"),
    SUNGKYUNKWAN_UNIVERSITY("성균관대학교", "skku.edu"),
    HANYANG_UNIVERSITY("한양대학교", "hanyang.ac.kr"),
    EWHA_WOMANS_UNIVERSITY("이화여자대학교", "ewha.ac.kr"),
    CHUNGANG_UNIVERSITY("중앙대학교", "cau.ac.kr"),
    KYUNGHEE_UNIVERSITY("경희대학교", "khu.ac.kr"),
    UNIVERSITY_OF_SEOUL("서울시립대학교", "uos.ac.kr"),
    GACHON_UNIVERSITY("가천대학교", "gachon.ac.kr"),
    HANYANG_UNIVERSITY_ERICA("한양대학교 ERICA캠퍼스", "hanyang.ac.kr"),
    AJOU_UNIVERSITY("아주대학교", "ajou.ac.kr"),
    DANKOOK_UNIVERSITY("단국대학교", "dankook.ac.kr"),
    INHA_UNIVERSITY("인하대학교", "inha.ac.kr"),
    YONSEI_UNIVERSITY_SONGDO("연세대학교 송도캠퍼스", "yonsei.ac.kr"),
    PUSAN_NATIONAL_UNIVERSITY("부산대학교", "pusan.ac.kr"),
    DONG_A_UNIVERSITY("동아대학교", "donga.ac.kr"),
    KYUNGPOOK_NATIONAL_UNIVERSITY("경북대학교", "knu.ac.kr"),
    YEUNGNAM_UNIVERSITY("영남대학교", "yu.ac.kr"),
    KAIST("카이스트", "kaist.ac.kr"),
    CHUNGNAM_NATIONAL_UNIVERSITY("충남대학교", "o.cnu.ac.kr", "cnu.ac.kr"),
    CHONNAM_NATIONAL_UNIVERSITY("전남대학교", "jnu.ac.kr"),
    GIST("광주과학기술원", "gist.ac.kr"),
    UNIST("울산과학기술원", "unist.ac.kr"),
    KANGWON_NATIONAL_UNIVERSITY("강원대학교", "kangwon.ac.kr"),
    CHUNGBUK_NATIONAL_UNIVERSITY("충북대학교", "chungbuk.ac.kr"),
    KONKUK_UNIVERSITY_GLOCAL("건국대학교 글로컬캠퍼스", "kku.ac.kr"),
    CHONBUK_NATIONAL_UNIVERSITY("전북대학교", "jbnu.ac.kr"),
    GYEONGSANG_NATIONAL_UNIVERSITY("경상대학교", "gnu.ac.kr"),
    POSTECH("포항공과대학교", "postech.ac.kr");

    private final String universityName;
    private final List<String> emailDomains;

    University(String universityName, String... emailDomains) {
        this.universityName = universityName;
        this.emailDomains = Arrays.asList(emailDomains);
    }

    public static University fromEmail(String email) {
        String domain = email.split("@")[1];
        return Arrays.stream(University.values())
                .filter(it -> it.getEmailDomains().contains(domain))
                .findAny()
                .orElseThrow(() -> new BadRequestException("지원되지 않는 학교 이메일입니다."));
    }
}
