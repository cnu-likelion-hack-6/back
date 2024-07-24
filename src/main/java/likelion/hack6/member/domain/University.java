package likelion.hack6.member.domain;

import java.util.Arrays;
import java.util.List;
import likelion.hack6.common.exception.type.BadRequestException;
import lombok.Getter;

@Getter
public enum University {

    CATHOLIC_UNIVERSITY("가톨릭대학교", "catholic.ac.kr"),
    GACHON_UNIVERSITY("가천대학교", "gachon.ac.kr"),
    KANGNAM_UNIVERSITY("강남대학교", "kangnam.ac.kr"),
    KANGWON_NATIONAL_UNIVERSITY("강원대학교", "kangwon.ac.kr"),
    KONKUK_UNIVERSITY("건국대학교", "konkuk.ac.kr"),
    KONKUK_UNIVERSITY_GLOCAL("건국대학교 글로컬캠퍼스", "kku.ac.kr"),
    GYEONGSANG_NATIONAL_UNIVERSITY("경상국립대학교", "gnu.ac.kr"),
    KYUNGPOOK_NATIONAL_UNIVERSITY("경북대학교", "knu.ac.kr"),
    KYUNGHEE_UNIVERSITY("경희대학교", "khu.ac.kr"),
    KYEMYUNG_UNIVERSITY("계명대학교", "kmu.ac.kr"),
    KOREA_UNIVERSITY("고려대학교", "korea.ac.kr"),
    //KOREA_UNIVERSITY_SEJONG("고려대학교(세종)", "korea.ac.kr"),
    KWANGWOON_UNIVERSITY("광운대학교", "kw.ac.kr"),
    GIST("광주과학기술원", "gist.ac.kr"),
    KOOKMIN_UNIVERSITY("국민대학교", "kookmin.ac.kr"),
    KUMOH_INSTITUTE_OF_TECHNOLOGY("금오공과대학교", "kumoh.ac.kr"),
    NAMSEOUL_UNIVERSITY("남서울대학교", "nsu.ac.kr"),
    DANKOOK_UNIVERSITY("단국대학교", "dankook.ac.kr"),
    DUKSUNG_WOMENS_UNIVERSITY("덕성여자대학교", "duksung.ac.kr"),
    DONGGUK_UNIVERSITY("동국대학교", "dongguk.ac.kr"),
    DONGDUK_WOMENS_UNIVERSITY("동덕여자대학교", "dongduk.ac.kr"),
    DONG_A_UNIVERSITY("동아대학교", "donga.ac.kr"),
    MYONGJI_UNIVERSITY_SEOUL("명지대학교(서울)", "mju.ac.kr"),
    //MYONGJI_UNIVERSITY_YONGIN("명지대학교(자연)", "mju.ac.kr"),
    PUSAN_NATIONAL_UNIVERSITY("부산대학교", "pusan.ac.kr"),
    BUSAN_UNIVERSITY_OF_FOREIGN_STUDIES("부산외국어대학교", "pufs.ac.kr"),
    SAMYUK_UNIVERSITY("삼육대학교", "syuin.ac.kr"),
    SANGMYUNG_UNIVERSITY_SEOUL("상명대학교(서울)", "sangmyung.kr"),
    //SANGMYUNG_UNIVERSITY_CHEONAN("상명대학교(천안)", "sangmyung.kr"),
    SEOUL_TECH("서울과학기술대학교", "seoultech.ac.kr"),
    SEOUL_NATIONAL_UNIVERSITY("서울대학교", "snu.ac.kr"),
    SEOUL_WOMENS_UNIVERSITY("서울여자대학교", "swu.ac.kr"),
    SEJONG_UNIVERSITY("세종대학교", "sejong.ac.kr"),
    SUNGKYL_KYUL_UNIVERSITY("성결대학교", "sungkyul.ac.kr"),
    SUNGKYUNKWAN_UNIVERSITY("성균관대학교", "skku.edu"),
    SUNGSHIN_WOMENS_UNIVERSITY("성신여자대학교", "sungshin.ac.kr"),
    SUNCHONHYANG_UNIVERSITY("순천향대학교", "scnu.ac.kr"),
    SOONGSIL_UNIVERSITY("숭실대학교", "soongsil.ac.kr"),
    AJOU_UNIVERSITY("아주대학교", "ajou.ac.kr"),
    YONSEI_UNIVERSITY("연세대학교", "yonsei.ac.kr"),
    YONSEI_UNIVERSITY_SONGDO("연세대학교 송도캠퍼스", "yonsei.ac.kr"),
    YEUNGNAM_UNIVERSITY("영남대학교", "yu.ac.kr", "yun.ac.kr"),
    YOUNGJIN_UNIVERSITY("영진대학교", "ync.ac.kr"),
    UNIST("울산과학기술원", "unist.ac.kr"),
    EWHA_WOMANS_UNIVERSITY("이화여자대학교", "ewha.ac.kr", "ewhain.net"),
    INCHON_NATIONAL_UNIVERSITY("인천대학교", "inu.ac.kr"),
    INHA_UNIVERSITY("인하대학교", "inha.ac.kr", "inha.edu"),
    CHONNAM_NATIONAL_UNIVERSITY("전남대학교", "jnu.ac.kr"),
    CHONBUK_NATIONAL_UNIVERSITY("전북대학교", "jbnu.ac.kr"),
    CHUNGANG_UNIVERSITY("중앙대학교", "cau.ac.kr"),
    JUNG_BU_UNIVERSITY("중부대학교(고양)", "jmail.ac.kr"),
    CHUNGBUK_NATIONAL_UNIVERSITY("충북대학교", "chungbuk.ac.kr"),
    CHUNGNAM_NATIONAL_UNIVERSITY("충남대학교", "o.cnu.ac.kr", "cnu.ac.kr"),
    KAIST("카이스트", "kaist.ac.kr"),
    //HANKUK_UNIVERSITY_OF_FOREIGN_STUDIES_GLOBAL("한국외국어대학교(글로벌)", "hufs.ac.kr"),
    HANKUK_UNIVERSITY_OF_FOREIGN_STUDIES_SEOUL("한국외국어대학교(서울)", "hufs.ac.kr"),
    KOREA_AEROSPACE_UNIVERSITY("한국항공대학교", "kau.ac.kr"),
    KOREA_NATIONAL_UNIVERSITY_OF_TRANSPORTATION("한국교통대학교", "ut.ac.kr"),
    HANDONG_GLOBAL_UNIVERSITY("한동대학교", "handong.edu"),
    HANBAT_NATIONAL_UNIVERSITY("한밭대학교", "hanbat.ac.kr"),
    HANSUNG_UNIVERSITY("한성대학교", "hansung.ac.kr"),
    HANYANG_UNIVERSITY("한양대학교", "hanyang.ac.kr"),
    //HANYANG_UNIVERSITY_ERICA("한양대학교 ERICA캠퍼스", "hanyang.ac.kr"),
    POSTECH("포항공과대학교", "postech.ac.kr"),
    ;

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
