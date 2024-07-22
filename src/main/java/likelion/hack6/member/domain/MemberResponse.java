package likelion.hack6.member.domain;

import lombok.Getter;

@Getter
public class MemberResponse {

    private final Long memberId;
    private final String phone;
    private final String kakaoId;
    private final String name;
    private final int age;
    private final String gender;
    private final String univ;
    private final String department;
    private final String state;
    private final int grade;

    public MemberResponse(Long memberId, String phone, String kakaoId, String name, int age, String gender, String univ, String department, String state, int grade) {
        this.memberId = memberId;
        this.phone = phone;
        this.kakaoId = kakaoId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.univ = univ;
        this.department = department;
        this.state = state;
        this.grade = grade;
    }
}
