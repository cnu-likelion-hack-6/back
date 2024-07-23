package likelion.hack6.match.application;

import java.util.Optional;
import likelion.hack6.common.exception.type.ConflictException;
import likelion.hack6.match.application.response.SendMatchRequestResult;
import likelion.hack6.match.domain.Match;
import likelion.hack6.match.domain.MatchRepository;
import likelion.hack6.match.domain.MatchRequest;
import likelion.hack6.match.domain.MatchRequestRepository;
import likelion.hack6.match.domain.filter.Filter;
import likelion.hack6.match.domain.filter.FilterRepository;
import likelion.hack6.member.domain.Member;
import likelion.hack6.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MatchService {

    private final MemberRepository memberRepository;
    private final FilterRepository filterRepository;
    private final MatchRequestRepository matchRequestRepository;
    private final MatchRepository matchRepository;

    public SendMatchRequestResult sendRequest(Member member, Long targetMemberId) {
        Member target = memberRepository.getById(targetMemberId);

        // TODO : if required, verify member's filter match with targetMember's filter

        if (matchRequestRepository.existsByRequesterAndReceiver(target, member)) {
            throw new ConflictException("이미 해당 사용자에게 밥약 신청을 보냈습니다.");
        }

        // 만약 상대방이 이미 나에게 match request 를 보냈다면, match 성공!
        Optional<MatchRequest> otherRequest = matchRequestRepository.findByRequesterAndReceiver(target, member);
        if (otherRequest.isPresent()) {
            MatchRequest matchRequest = otherRequest.get();
            Match match = matchRequest.accept();
            matchRepository.save(match);
            return SendMatchRequestResult.MATCHED;
        }

        // 첫 요청이라면, match request 생성
        Filter filter = filterRepository.getByMember(member);
        Filter targetFilter = filterRepository.getByMember(target);
        MatchRequest matchRequest = new MatchRequest(filter, targetFilter);
        matchRequestRepository.save(matchRequest);
        return SendMatchRequestResult.REQUESTED;
    }
}
