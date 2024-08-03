package likelion.hack6.match.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import likelion.hack6.auth.Auth;
import likelion.hack6.match.application.FilterQueryService;
import likelion.hack6.match.application.FilterService;
import likelion.hack6.match.application.response.MyFilterResponse;
import likelion.hack6.match.presentation.request.CreateFilterRequest;
import likelion.hack6.match.presentation.request.UpdateMatchSideRequest;
import likelion.hack6.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "JWT")
@Tag(name = "검색 필터 API")
@RequiredArgsConstructor
@RequestMapping("/filters")
@RestController
public class FilterController {

    private final FilterService filterService;
    private final FilterQueryService filterQueryService;

    @Operation(summary = "필터 최초 설정하였는지 확인")
    @GetMapping
    public boolean hasFilter(
            @Auth Member member
    ) {
        return filterQueryService.hasFilter(member);
    }

    @Operation(summary = "필터 설정(생성/수정)")
    @PostMapping
    public void createFilter(
            @Auth Member member,
            @Valid @RequestBody CreateFilterRequest request
    ) {
        filterService.createFilter(member, request.toCommand());
    }

    @Operation(summary = "밥약 신청 상태 변경 (신청할래요, 먹을래요, 둘 다 좋아요)")
    @PutMapping("/side")
    public void updateMatchSide(
            @Auth Member member,
            @Valid @RequestBody UpdateMatchSideRequest request
    ) {
        filterService.updateMatchSide(member, request.state());
    }

    @Operation(summary = "내 필터 상태 조회")
    @PutMapping("/my")
    public ResponseEntity<MyFilterResponse> getMyFilter(
            @Auth Member member
    ) {
        return ResponseEntity.ok(filterQueryService.getMyFilter(member));
    }
}
