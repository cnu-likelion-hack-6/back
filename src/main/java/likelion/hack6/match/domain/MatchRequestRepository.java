package likelion.hack6.match.domain;

import java.util.List;
import java.util.Optional;
import likelion.hack6.common.exception.type.NotFoundException;
import likelion.hack6.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchRequestRepository extends JpaRepository<MatchRequest, Long> {

    default MatchRequest getById(Long id) {
        return findById(id).orElseThrow(() ->
                new NotFoundException("해당 id(=%d)를 가진 Match Request 가 없습니다.".formatted(id)));
    }

    List<MatchRequest> findAllByRequester(Member member);

    @Query("SELECT mr FROM MatchRequest mr WHERE mr.receiver = :member AND mr.status = 'NOT_PROCESSED' ORDER BY mr.createdDate DESC")
    List<MatchRequest> findAllAcceptableByReceiverOrderByCreatedDateDesc(Member member);

    Optional<MatchRequest> findByRequesterAndReceiver(Member requester, Member receiver);

    boolean existsByRequesterAndReceiver(Member requester, Member receiver);
}
