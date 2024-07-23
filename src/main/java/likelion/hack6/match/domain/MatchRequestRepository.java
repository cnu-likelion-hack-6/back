package likelion.hack6.match.domain;

import java.util.List;
import likelion.hack6.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRequestRepository extends JpaRepository<MatchRequest, Long> {

    List<MatchRequest> findAllByRequester(Member member);
}
