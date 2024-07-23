package likelion.hack6.match.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record SendMatchRequestRequest(
        @Schema(description = "밥약 신청할 상대방 회원 id")
        @NotNull Long targetMemberId
) {
}
