package likelion.hack6.member.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CertificateEmailRequest(
        @Schema(description = "이메일로 전송된 4자리 인증번호")
        @NotBlank String code
) {
}
