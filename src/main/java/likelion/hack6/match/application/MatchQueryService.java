package likelion.hack6.match.application;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import likelion.hack6.match.application.response.MatchHistoryResponse;
import likelion.hack6.match.application.response.MatchRequestInfoResponse;
import likelion.hack6.match.application.response.MatchableMemberResponse;
import likelion.hack6.match.application.response.ThanksMessageResponse;
import likelion.hack6.match.domain.Match;
import likelion.hack6.match.domain.MatchRepository;
import likelion.hack6.match.domain.MatchRequest;
import likelion.hack6.match.domain.MatchRequestRepository;
import likelion.hack6.match.domain.filter.Filter;
import likelion.hack6.match.domain.filter.FilterRepository;
import likelion.hack6.member.domain.Member;
import likelion.hack6.member.domain.University;
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
        University university = member.getProfile().getUniversityEmail().getUniversity();
        List<Filter> othersMemberFilters = filterRepository.findAllByUniversity(university);
        Map<Member, Filter> memberFilterMap = othersMemberFilters.stream()
                .collect(Collectors.toMap(Filter::getMember, Function.identity()));
        Set<Member> exceptMembers = new HashSet<>();
        exceptMembers.add(member);  // 자기 자신 제외

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

        List<Member> candidates = othersMemberFilters.stream()
                .filter(it -> it.match(filter))
                .map(Filter::getMember)
                .collect(Collectors.toList());
        candidates.removeAll(exceptMembers);
        Collections.shuffle(candidates);

        return candidates.stream()
                .map(candidate ->
                        MatchableMemberResponse.of(
                                candidate,
                                memberFilterMap.get(candidate).getMatchSideState())
                ).toList();
    }

    // 처리하지 않은 매치 요청 보기
    public List<MatchRequestInfoResponse> findAcceptableReceivedMatchRequest(Member member) {
        List<MatchRequest> acceptable = matchRequestRepository.findAllAcceptableByReceiverOrderByCreatedDateDesc(
                member);
        return acceptable.stream()
                .map(MatchRequestInfoResponse::from)
                .toList();
    }

    // 매치 이력 보기
    public List<MatchHistoryResponse> findMatchedMembers(Member member) {
        List<Match> matched = matchRepository.findAllByBuyerOrTakerOrderByCreatedDateDesc(member);
        return matched.stream()
                .map(MatchHistoryResponse::of)
                .toList();
    }

    // 받은 감사 인사 보기
    public List<ThanksMessageResponse> findGivenThanksMessages(Member member) {
        return matchRepository.findAllByBuyerOrTakerOrderByCreatedDateDesc(member)
                .stream()
                .map(it -> ThanksMessageResponse.of(it, member))
                .toList();
    }

}
