package likelion.hack6.member.presentation;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import likelion.hack6.auth.Auth;
import likelion.hack6.auth.TokenResponse;
import likelion.hack6.auth.TokenService;
import likelion.hack6.mail.EmailService;
import likelion.hack6.member.application.MemberService;
import likelion.hack6.member.domain.Member;
import likelion.hack6.member.domain.University;
import likelion.hack6.member.presentation.request.CertificateEmailRequest;
import likelion.hack6.member.presentation.request.LoginRequest;
import likelion.hack6.member.presentation.request.SendEmailCertificationCodeRequest;
import likelion.hack6.member.presentation.request.SetKakaoIdRequest;
import likelion.hack6.member.presentation.request.SetProfileRequest;
import likelion.hack6.member.presentation.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 API")
@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final TokenService tokenService;
    private final MemberService memberService;
    private final EmailService emailService;

    @Operation(summary = "회원가입")
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

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public TokenResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        Long memberId = memberService.login(request.toCommand());
        return tokenService.createToken(memberId);
    }

    @SecurityRequirement(name = "JWT")
    @Operation(summary = "카카오톡 ID 설정")
    @PostMapping("/profile/kakaoId")
    public void setKakaoId(
            @Auth Member member,
            @Valid @RequestBody SetKakaoIdRequest request
    ) {
        memberService.setKakaoId(member, request.kakaoId());
    }

    @SecurityRequirement(name = "JWT")
    @Operation(summary = "이메일을 통한 대학교 이름 조회")
    @GetMapping("/university-by-email")
    public String getUniversityByEmail(
            @Auth Member member,
            @Parameter(in = PATH, required = true, description = "이메일", example = "mallang@cnu.ac.kr")
            @RequestParam("email") String email
    ) {
        University university = University.fromEmail(email);
        return university.getUniversityName();
    }

    // TODO return String(확인용) -> void
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "이메일 인증번호 전송")
    @PostMapping("/profile/email/send-code")
    public String sendEmailCertificationCode(
            @Auth Member member,
            @Valid @RequestBody SendEmailCertificationCodeRequest request
    ) {
        String code = memberService.sendEmailCertificationCode(member, request.email());
        emailService.sendEmail(request.email(), code);
        return code;
    }

    @SecurityRequirement(name = "JWT")
    @Operation(summary = "이메일 인증번호 확인")
    @PostMapping("/profile/email/certificate")
    public void certificateEmail(
            @Auth Member member,
            @Valid @RequestBody CertificateEmailRequest request
    ) {
        memberService.certificateEmail(member, request.code());
    }

    @SecurityRequirement(name = "JWT")
    @Operation(summary = "프로필 설정")
    @PostMapping("/profile")
    public void setProfile(
            @Auth Member member,
            @Valid @RequestBody SetProfileRequest request
    ) {
        memberService.setProfile(member, request.toCommand());
    }

    @SecurityRequirement(name = "JWT")
    @Operation(summary = "프로필 설정여부 확인")
    @GetMapping("/profile")
    public boolean isProfileSetup(
            @Auth Member member
    ) {
        return memberService.isProfileSetup(member);
    }
}
