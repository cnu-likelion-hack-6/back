package likelion.hack6.match.domain;

import java.util.List;
import likelion.hack6.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findAllByBuyerOrTaker(Member member);

    List<Match> findAllByBuyerOrTakerOrderByCreatedDateDesc(Member member);
}
