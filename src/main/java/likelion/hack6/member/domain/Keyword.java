package likelion.hack6.member.domain;

public enum Keyword {

    CAREERS("진로"),
    EMPLOYMENT("취업"),
    ACADEMICS("학업"),
    RELATIONSHIPS("연애"),
    FRIENDS("친구"),
    ETC("기타"),
    ;

    private final String koreanName;

    Keyword(String koreanName) {
        this.koreanName = koreanName;
    }
}
