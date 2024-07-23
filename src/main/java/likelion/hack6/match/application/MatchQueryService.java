package likelion.hack6.match.application;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import likelion.hack6.match.application.response.MatchableMemberResponse;
import likelion.hack6.match.domain.Match;
import likelion.hack6.match.domain.MatchRepository;
import likelion.hack6.match.domain.MatchRequest;
import likelion.hack6.match.domain.MatchRequestRepository;
import likelion.hack6.match.domain.filter.Filter;
import likelion.hack6.match.domain.filter.FilterRepository;
import likelion.hack6.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MatchQueryService {

    private final FilterRepository filterRepository;
    private final MatchRepository matchRepository;
    private final MatchRequestRepository matchRequestRepository;

    public List<MatchableMemberResponse> findMatchableMembers(Member member) {
        Filter filter = filterRepository.getByMember(member);
        List<Filter> othersMemberFilters = filterRepository.findAll();
        Set<Member> exceptMembers = new HashSet<>();
        exceptMembers.add(member);  // 자기 자신 제외

        // TODO: 이거 물어보기
        // 이미 밥약을 한 사람(수락이던, 거절이던) 제외
        List<Match> alreadyMatched = matchRepository.findAllByBuyerOrTaker(member);
        for (Match match : alreadyMatched) {
            exceptMembers.add(match.getBuyer());
            exceptMembers.add(match.getTaker());
        }
        // 이미 밥약 요청을 보낸 사람 제외 (내가 요청을 받은 경우에는, 해당 요청을 보낸 사람은 제외되지 않음)
        List<MatchRequest> alreadyRequested = matchRequestRepository.findAllByRequester(member);
        for (MatchRequest matchRequest : alreadyRequested) {
            exceptMembers.add(matchRequest.getReceiver());
        }
        Set<Member> candidates = othersMemberFilters.stream()
                .filter(it -> it.matchable(filter))
                .map(Filter::getMember)
                .collect(Collectors.toSet());
        candidates.removeAll(exceptMembers);
        return candidates.stream()
                .map(MatchableMemberResponse::from)
                .toList();
    }
}
