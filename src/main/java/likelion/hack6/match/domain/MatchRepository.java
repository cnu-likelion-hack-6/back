package likelion.hack6.match.domain;

import java.util.List;
import likelion.hack6.common.exception.type.NotFoundException;
import likelion.hack6.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {

    default Match getById(Long id) {
        return findById(id).orElseThrow(() ->
                new NotFoundException("해당 id(=%d)를 가진 Match 가 없습니다.".formatted(id)));
    }

    List<Match> findAllByBuyerOrTaker(Member member);

    List<Match> findAllByBuyerOrTakerOrderByCreatedDateDesc(Member member);
}
