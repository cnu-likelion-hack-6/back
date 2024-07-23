package likelion.hack6.member.domain;

import likelion.hack6.common.exception.type.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberValidator {

    private final MemberRepository memberRepository;

    public void validateDuplicatedPhone(String phone) {
        if (memberRepository.existsByPhone(phone)) {
            throw new ConflictException("이미 존재하는 전화번호입니다.");
        }
    }
}
