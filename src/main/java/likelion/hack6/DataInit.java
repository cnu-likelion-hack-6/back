//package likelion.hack6;
//
//import jakarta.annotation.PostConstruct;
//import java.util.Set;
//import likelion.hack6.auth.TokenResponse;
//import likelion.hack6.auth.TokenService;
//import likelion.hack6.match.domain.filter.AgeCondition;
//import likelion.hack6.match.domain.filter.DepartmentCondition;
//import likelion.hack6.match.domain.filter.Filter;
//import likelion.hack6.match.domain.filter.FilterRepository;
//import likelion.hack6.match.domain.filter.GenderCondition;
//import likelion.hack6.match.domain.filter.GradeCondition;
//import likelion.hack6.match.domain.filter.MatchSideState;
//import likelion.hack6.member.domain.Gender;
//import likelion.hack6.member.domain.Member;
//import likelion.hack6.member.domain.MemberRepository;
//import likelion.hack6.member.domain.Profile;
//import likelion.hack6.member.domain.ProfileIcon;
//import likelion.hack6.member.domain.StudentStatus;
//import likelion.hack6.member.domain.UniversityEmail;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Transactional
//@RequiredArgsConstructor
//@Component
//public class DataInit {
//
//    private final MemberRepository memberRepository;
//    private final FilterRepository filterRepository;
//    private final TokenService tokenService;
//
//    @PostConstruct
//    public void init() {
//        Member save = memberRepository.save(new Member("01011111111", "1234"));
//        Member save1 = memberRepository.save(new Member("01011111112", "1234"));
//        save.setupProfile(new Profile(
//                "test1",
//                new UniversityEmail("test1@o.cnu.ac.kr", "1"),
//                "이건하",
//                Gender.MAN,
//                "인공지능",
//                19,
//                StudentStatus.CURRENT,
//                2,
//                21,
//                ProfileIcon.ICON_1,
//                "q",
//                Set.of(),
//                true
//        ));
//
//        save1.setupProfile(new Profile(
//                "test2",
//                new UniversityEmail("2@o.cnu.ac.kr", "1"),
//                "황현정",
//                Gender.GIRL,
//                "컴융",
//                21,
//                StudentStatus.CURRENT,
//                1,
//                20,
//                ProfileIcon.ICON_1,
//                "q",
//                Set.of(),
//                true
//        ));
//        memberRepository.save(save);
//        memberRepository.save(save1);
//        filterRepository.save(new Filter(
//                save,
//                new AgeCondition(1, 100),
//                GenderCondition.BOTH,
//                new GradeCondition(1, 100),
//                DepartmentCondition.BOTH,
//                MatchSideState.BOTH
//        ));
//        filterRepository.save(new Filter(
//                save1,
//                new AgeCondition(1, 100),
//                GenderCondition.BOTH,
//                new GradeCondition(1, 100),
//                DepartmentCondition.BOTH,
//                MatchSideState.BOTH
//        ));
//        TokenResponse token = tokenService.createToken(save.getId());
//        TokenResponse token1 = tokenService.createToken(save1.getId());
//        System.out.println("token: " + token);
//        System.out.println("token1: " + token1);
//    }
//}
