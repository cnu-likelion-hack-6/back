package likelion.hack6.match.domain;

import java.util.List;
import java.util.Optional;
import likelion.hack6.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRequestRepository extends JpaRepository<MatchRequest, Long> {

    List<MatchRequest> findAllByRequester(Member member);

    Optional<MatchRequest> findByRequesterAndReceiver(Member requester, Member receiver);

    boolean existsByRequesterAndReceiver(Member requester, Member receiver);
}
