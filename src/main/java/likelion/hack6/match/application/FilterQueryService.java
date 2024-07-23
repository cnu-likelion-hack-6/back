package likelion.hack6.match.application;

import likelion.hack6.match.domain.filter.FilterRepository;
import likelion.hack6.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FilterQueryService {

    private final FilterRepository filterRepository;

    public boolean hasFilter(Member member) {
        return filterRepository.existsByMember(member);
    }
}
