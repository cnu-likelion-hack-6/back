package likelion.hack6.member.application;

import likelion.hack6.member.application.command.LoginCommand;
import likelion.hack6.member.application.command.SignupCommand;
import likelion.hack6.member.domain.Member;
import likelion.hack6.member.domain.MemberRepository;
import likelion.hack6.member.domain.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;

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
}
