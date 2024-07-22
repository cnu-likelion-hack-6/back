package likelion.hack6.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String phone;
    private String password;

    @Column(nullable = false)
    private String kakaoId;
    private String name;
    private int age;
    private String gender;
    private String univ;
    private String department;
    private String state;

    private int grade;

    public Member(String phone,String password ,String kakaoId, String name, int age, String gender, String univ, String department, String state, int grade) {
        this.phone = phone;
        this.password = password;
        this.kakaoId = kakaoId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.univ = univ;
        this.department = department;
        this.state = state;
        this.grade = grade;
    }
    //로그인
    public void login(String password) {
        if (this.password.equals(password)) {
            return;
        }
        throw new IllegalArgumentException("Wrong password");
    }

}
