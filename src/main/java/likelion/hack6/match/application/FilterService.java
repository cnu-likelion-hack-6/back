package likelion.hack6.match.application;

import jakarta.transaction.Transactional;
import java.util.Optional;
import likelion.hack6.match.application.command.CreateFilterCommand;
import likelion.hack6.match.domain.filter.Filter;
import likelion.hack6.match.domain.filter.FilterRepository;
import likelion.hack6.match.domain.filter.MatchSideState;
import likelion.hack6.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class FilterService {

    private final FilterRepository filterRepository;

    public void createFilter(Member member, CreateFilterCommand command) {
        Optional<Filter> byMember = filterRepository.findByMember(member);
        if (byMember.isEmpty()) {
            Filter filter = command.toFilter(member);
            filterRepository.save(filter);
            return;
        }
        Filter filter = byMember.get();
        filter.update(
                command.ageCondition(),
                command.genderCondition(),
                command.gradeCondition(),
                command.departmentCondition()
        );
        filterRepository.save(filter);
    }
    
    public void updateMatchSide(Member member, MatchSideState matchSideState) {
        Filter filter = filterRepository.getByMember(member);
        filter.updateMatchSide(matchSideState);
        filterRepository.save(filter);
    }
}
