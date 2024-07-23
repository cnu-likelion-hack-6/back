package likelion.hack6.member.presentation;

import jakarta.validation.Valid;
import java.net.URI;
import likelion.hack6.auth.Auth;
import likelion.hack6.auth.TokenResponse;
import likelion.hack6.auth.TokenService;
import likelion.hack6.member.application.MemberService;
import likelion.hack6.member.domain.Member;
import likelion.hack6.member.presentation.request.CertificateEmailRequest;
import likelion.hack6.member.presentation.request.LoginRequest;
import likelion.hack6.member.presentation.request.SendEmailCertificationCodeRequest;
import likelion.hack6.member.presentation.request.SetKakaoIdRequest;
import likelion.hack6.member.presentation.request.SetProfileRequest;
import likelion.hack6.member.presentation.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController implements MemberApi {

    private final TokenService tokenService;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<TokenResponse> signup(
            @Valid @RequestBody SignupRequest request
    ) {
        Long memberId = memberService.signup(request.toCommand());
        TokenResponse token = tokenService.createToken(memberId);
        return ResponseEntity
                .created(URI.create("/members/" + memberId))
                .body(token);
    }

    @PostMapping("/login")
    public TokenResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        Long memberId = memberService.login(request.toCommand());
        return tokenService.createToken(memberId);
    }

    @PostMapping("/profile/kakaoId")
    public void setKakaoId(
            @Auth Member member,
            @Valid @RequestBody SetKakaoIdRequest request
    ) {
        memberService.setKakaoId(member, request.kakaoId());
    }

    @PostMapping("/profile/email/send-code")
    public String sendEmailCertificationCode(
            @Auth Member member,
            @Valid @RequestBody SendEmailCertificationCodeRequest request
    ) {
        return memberService.sendEmailCertificationCode(member, request.email());
    }

    @PostMapping("/profile/email/certificate")
    public void certificateEmail(
            @Auth Member member,
            @Valid @RequestBody CertificateEmailRequest request
    ) {
        memberService.certificateEmail(member, request.code());
    }

    @PostMapping("/profile")
    public void setProfile(
            @Auth Member member,
            @Valid @RequestBody SetProfileRequest request
    ) {
        memberService.setProfile(member, request.toCommand());
    }
}
