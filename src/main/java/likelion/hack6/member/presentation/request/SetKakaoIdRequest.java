package likelion.hack6.member.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record SetKakaoIdRequest(
        @Schema(description = "카카오 ID")
        @NotBlank String kakaoId
) {
}
