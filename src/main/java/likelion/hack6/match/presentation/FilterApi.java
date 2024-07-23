package likelion.hack6.match.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import likelion.hack6.auth.Auth;
import likelion.hack6.match.presentation.request.CreateFilterRequest;
import likelion.hack6.match.presentation.request.UpdateFilterRequest;
import likelion.hack6.match.presentation.request.UpdateMatchSideRequest;
import likelion.hack6.member.domain.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "필터 API")
public interface FilterApi {

    @Operation(summary = "필터 최초 설정하였는지 확인")
    @GetMapping
    boolean hasFilter(
            @Auth Member member
    );

    @Operation(summary = "필터 최초 설정")
    @PostMapping
    void createFilter(
            @Auth Member member,
            @Valid @RequestBody CreateFilterRequest request
    );

    @Operation(summary = "필터 업데이트")
    @PutMapping
    void updateFilter(
            @Auth Member member,
            @Valid @RequestBody UpdateFilterRequest request
    );

    @Operation(summary = "밥약 신청 상태 변경 (신청할래요, 먹을래요, 둘 다 좋아요)")
    @PutMapping("/side")
    void updateMatchSide(
            @Auth Member member,
            @Valid @RequestBody UpdateMatchSideRequest request
    );
}
