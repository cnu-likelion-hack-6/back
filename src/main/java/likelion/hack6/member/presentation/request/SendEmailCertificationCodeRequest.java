package likelion.hack6.member.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record SendEmailCertificationCodeRequest(
        @Schema(description = "이메일 (sample@cnu.ac.kr)")
        @NotBlank String email
) {
}
