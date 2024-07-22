package likelion.hack6.member.domain;

import likelion.hack6.common.ConflictException;
import likelion.hack6.common.NotFoundException;
import likelion.hack6.common.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    //멤버 객체 저장하는 레퍼지토리
    private final MemberRepository memberRepository;

    //회원가입 메소드
    public Long signup(String phone, String password, String kakaoId, String name, int age, String gender, String univ, String department, String state, int grade) {
        memberRepository.findByPhone(phone)
                .ifPresent(it -> {
                    throw new ConflictException("해당 아이디로 이미 가입한 회원이 있습니다.");
                });
        //새로운 멤버 생성
        Member member = new Member(phone, password, kakaoId, name, age, gender, univ, department, state, grade);
        //새로 만든 멤버 아이디 레퍼지토리에 저장
        return memberRepository.save(member).getId();
    }

    //로그인 메소드
    public Long login(String phone, String password) {
        //전화 번호로 레퍼지토리에서 해당 멤버 찾기
        Member member = memberRepository.findByPhone(phone)
                .orElseThrow(() -> new UnAuthorizedException("해당 번호로 가입한 회원이 없습니다."));
        //로그인
        member.login(password);
        //멤버의 아이디 반환
        return member.getId();
    }

    //전화 번호로 멤버 찾는 메소드
    public MemberResponse findByPhone(String phone) {
        Member member = memberRepository.findByPhone(phone)
                .orElseThrow(() -> new NotFoundException("해당 번호로 가입한 회원이 없습니다."));
        return new MemberResponse(
                member.getId(),
                member.getPhone(),
                member.getKakaoId(),
                member.getName(),
                member.getAge(),
                member.getGender(),
                member.getUniv(),
                member.getDepartment(),
                member.getState(),
                member.getGrade()
        );
    }
}
