package likelion.hack6.match.presentation;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import likelion.hack6.auth.Auth;
import likelion.hack6.match.application.MatchQueryService;
import likelion.hack6.match.application.MatchService;
import likelion.hack6.match.application.response.MatchHistoryResponse;
import likelion.hack6.match.application.response.MatchRequestInfoResponse;
import likelion.hack6.match.application.response.MatchableMemberResponse;
import likelion.hack6.match.application.response.ThanksMessageResponse;
import likelion.hack6.match.presentation.request.SendMatchRequestRequest;
import likelion.hack6.match.presentation.request.WriteThankMessageRequest;
import likelion.hack6.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "JWT")
@Tag(name = "밥약(매치) API")
@RequiredArgsConstructor
@RequestMapping("/matches")
@RestController
public class MatchController {

    private final MatchQueryService matchQueryService;
    private final MatchService matchService;

    @Operation(summary = "밥약(매치) 가능한 사람들 조회")
    @GetMapping("/candidates")
    public List<MatchableMemberResponse> findMatchableMembers(
            @Auth Member member
    ) {
        return matchQueryService.findMatchableMembers(member);
    }

    @Operation(summary = "처리하지 않은 밥약(매치) 요청 보기")
    @GetMapping("/acceptable-match-request")
    public List<MatchRequestInfoResponse> findAcceptableReceivedMatchRequest(
            @Auth Member member
    ) {
        return matchQueryService.findAcceptableReceivedMatchRequest(member);
    }

    @Operation(summary = "밥약(매치) 이력 조회")
    @GetMapping
    public List<MatchHistoryResponse> findMatchedMembers(
            @Auth Member member
    ) {
        return matchQueryService.findMatchedMembers(member);
    }

    @Operation(summary = "받은 감사 인사 조회")
    @GetMapping("/thanks")
    public List<ThanksMessageResponse> findGivenThanksMessages(
            @Auth Member member
    ) {
        return matchQueryService.findGivenThanksMessages(member);
    }

    @Operation(summary = "밥약(매치) 요청 보내기")
    @PostMapping
    public void sendRequest(
            @Auth Member member,
            @Valid @RequestBody SendMatchRequestRequest request
    ) {
        matchService.sendRequest(member, request.targetMemberId());
    }

    @Operation(summary = "밥약(매치) 요청 수락")
    @PostMapping("/{matchRequestId}/accept")
    public void accept(
            @Auth Member member,
            
            @Parameter(in = PATH, required = true, description = "밥약(매치) 요청 id")
            @PathVariable("matchRequestId") Long matchRequestId
    ) {
        matchService.accept(member, matchRequestId);
    }

    @Operation(summary = "밥약(매치) 요청 거절")
    @PostMapping("/{matchRequestId}/reject")
    public void reject(
            @Auth Member member,

            @Parameter(in = PATH, required = true, description = "밥약(매치) 요청 id")
            @PathVariable("matchRequestId") Long matchRequestId
    ) {
        matchService.reject(member, matchRequestId);
    }

    @Operation(summary = "감사 인사 작성")
    @PostMapping("/{matchId}/thanks")
    public void writeThankMessage(
            @Auth Member member,

            @Parameter(in = PATH, required = true, description = "밥약(매치) id")
            @PathVariable("matchId") Long matchId,

            @Valid @RequestBody WriteThankMessageRequest request
    ) {
        matchService.writeThankMessage(member, matchId, request.message());
    }
}
