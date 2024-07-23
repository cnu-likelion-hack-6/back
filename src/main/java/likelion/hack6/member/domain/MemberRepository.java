package likelion.hack6.member.domain;

import java.util.Optional;
import likelion.hack6.common.exception.type.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member getById(Long id) {
        return findById(id).orElseThrow(() ->
                new NotFoundException("해당 id(=%d)를 가진 회원이 없습니다.".formatted(id)));
    }

    default Member getByPhone(String phone) {
        return findByPhone(phone).orElseThrow(() ->
                new NotFoundException("해당 번호(%s)로 가입된 회원을 찾을 수 없습니다.".formatted(phone)));
    }

    Optional<Member> findByPhone(String phone);

    boolean existsByPhone(String phone);
}
