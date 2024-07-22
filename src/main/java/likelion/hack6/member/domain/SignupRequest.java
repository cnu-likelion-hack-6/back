package likelion.hack6.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class SignupRequest {

    private String phone;
    private String password;
    private String name;
    private String profileImageUrl;

    public SignupRequest(String phone, String password, String name, String profileImageUrl) {
        this.phone = phone;
        this.password = password;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }
}

//회원가입시 입력하는 정보 중 전화번호와 비밀번호 빼고 나머지 프로필 정보를 한번에 묶어버려도 되는지,, 알아보고 수정해야함
