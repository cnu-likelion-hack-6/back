package likelion.hack6.match.domain.filter;

import java.util.Optional;
import likelion.hack6.common.exception.type.NotFoundException;
import likelion.hack6.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterRepository extends JpaRepository<Filter, Long> {

    boolean existsByMember(Member member);

    default Filter getByMember(Member member) {
        return findByMember(member).orElseThrow(() ->
                new NotFoundException("아직 필터를 생성하지 않으셨습니다. 필터 생성을 진행해주세요"));
    }

    Optional<Filter> findByMember(Member member);
}
