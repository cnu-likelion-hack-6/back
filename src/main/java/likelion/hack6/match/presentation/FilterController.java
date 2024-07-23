package likelion.hack6.match.presentation;

import jakarta.validation.Valid;
import likelion.hack6.auth.Auth;
import likelion.hack6.match.application.FilterQueryService;
import likelion.hack6.match.application.FilterService;
import likelion.hack6.match.presentation.request.CreateFilterRequest;
import likelion.hack6.match.presentation.request.UpdateFilterRequest;
import likelion.hack6.match.presentation.request.UpdateMatchSideRequest;
import likelion.hack6.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/filters")
@RestController
public class FilterController implements FilterApi {

    private final FilterService filterService;
    private final FilterQueryService filterQueryService;

    @GetMapping
    public boolean hasFilter(
            @Auth Member member
    ) {
        return filterQueryService.hasFilter(member);
    }

    @PostMapping
    public void createFilter(
            @Auth Member member,
            @Valid @RequestBody CreateFilterRequest request
    ) {
        filterService.createFilter(member, request.toCommand());
    }

    @PutMapping
    public void updateFilter(
            @Auth Member member,
            @Valid @RequestBody UpdateFilterRequest request
    ) {
        filterService.updateFilter(member, request.toCommand());
    }

    @PutMapping("/side")
    public void updateMatchSide(
            @Auth Member member,
            @Valid @RequestBody UpdateMatchSideRequest request
    ) {
        filterService.updateMatchSide(member, request.state());
    }
}
