package likelion.hack6.match.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import likelion.hack6.match.domain.filter.MatchSideState;

public record UpdateMatchSideRequest(
        @Schema(description = "변경할 검색 상태 (먹을래요, 사줄래요, 상관없어요)")
        @NotNull MatchSideState state
) {
}
