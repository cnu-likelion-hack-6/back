package likelion.hack6.match.domain;

import java.util.List;
import likelion.hack6.common.exception.type.NotFoundException;
import likelion.hack6.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchRepository extends JpaRepository<Match, Long> {

    default Match getById(Long id) {
        return findById(id).orElseThrow(() ->
                new NotFoundException("해당 id(=%d)를 가진 Match 가 없습니다.".formatted(id)));
    }

    @Query("SELECT m FROM Match m WHERE m.buyer = :member OR m.taker = :member")
    List<Match> findAllByBuyerOrTaker(Member member);

    @Query("SELECT m FROM Match m WHERE m.buyer = :member OR m.taker = :member ORDER BY m.createdDate DESC")
    List<Match> findAllByBuyerOrTakerOrderByCreatedDateDesc(Member member);
}
