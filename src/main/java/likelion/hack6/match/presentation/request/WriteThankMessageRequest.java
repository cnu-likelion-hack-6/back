package likelion.hack6.match.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record WriteThankMessageRequest(
        @Schema(description = "감사 인사 메세지")
        @NotBlank String message
) {
}
