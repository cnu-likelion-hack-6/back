package likelion.hack6.member.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import likelion.hack6.auth.Auth;
import likelion.hack6.auth.TokenResponse;
import likelion.hack6.member.domain.Member;
import likelion.hack6.member.presentation.request.CertificateEmailRequest;
import likelion.hack6.member.presentation.request.LoginRequest;
import likelion.hack6.member.presentation.request.SendEmailCertificationCodeRequest;
import likelion.hack6.member.presentation.request.SetKakaoIdRequest;
import likelion.hack6.member.presentation.request.SetProfileRequest;
import likelion.hack6.member.presentation.request.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "회원 API")
public interface MemberApi {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(hidden = true))),
    })
    @Operation(summary = "회원가입")
    @PostMapping
    ResponseEntity<TokenResponse> signup(
            @Valid @RequestBody SignupRequest request
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
    })
    @Operation(summary = "로그인")
    @PostMapping("/login")
    TokenResponse login(
            @Valid @RequestBody LoginRequest request
    );


    @Operation(summary = "카카오톡 ID 설정")
    @PostMapping("/profile/kakaoId")
    void setKakaoId(
            @Auth Member member,
            @Valid @RequestBody SetKakaoIdRequest request
    );

    // TODO String(확인용) -> void
    @Operation(summary = "이메일 인증번호 전송")
    @PostMapping("/profile/email/send-code")
    String sendEmailCertificationCode(
            @Auth Member member,
            @Valid @RequestBody SendEmailCertificationCodeRequest request
    );

    @Operation(summary = "이메일 인증번호 확인")
    @PostMapping("/profile/email/certificate")
    void certificateEmail(
            @Auth Member member,
            @Valid @RequestBody CertificateEmailRequest request
    );

    @Operation(summary = "프로필 설정")
    @PostMapping("/profile")
    void setProfile(
            @Auth Member member,
            @Valid @RequestBody SetProfileRequest request
    );
}
