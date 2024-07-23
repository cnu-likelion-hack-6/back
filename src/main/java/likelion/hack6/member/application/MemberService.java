package likelion.hack6.member.application;

import jakarta.transaction.Transactional;
import likelion.hack6.member.application.command.LoginCommand;
import likelion.hack6.member.application.command.SetProfileCommand;
import likelion.hack6.member.application.command.SignupCommand;
import likelion.hack6.member.domain.CodeGenerator;
import likelion.hack6.member.domain.Member;
import likelion.hack6.member.domain.MemberRepository;
import likelion.hack6.member.domain.MemberValidator;
import likelion.hack6.member.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;
    private final CodeGenerator codeGenerator;

    public Long signup(SignupCommand command) {
        Member member = command.toMember();
        member.signup(memberValidator);
        return memberRepository.save(member).getId();
    }

    public Long login(LoginCommand command) {
        Member member = memberRepository.getByPhone(command.phone());
        member.login(command.password());
        return member.getId();
    }

    public void setKakaoId(Member member, String kakaoId) {
        member.setKakaoId(kakaoId);
        memberRepository.save(member);
    }

    public String sendEmailCertificationCode(Member member, String email) {
        String code = codeGenerator.generate(4);
        member.sendEmailCertificationCode(email, code);
        memberRepository.save(member);
        return code;
    }

    public void certificateEmail(Member member, String code) {
        member.certificateEmail(code);
        memberRepository.save(member);
    }

    public void setProfile(Member member, SetProfileCommand command) {
        Profile profile = command.toProfile(member);
        member.setupProfile(profile);
        memberRepository.save(member);
    }
}
