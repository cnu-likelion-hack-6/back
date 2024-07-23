package likelion.hack6.member.application.command;

import java.util.Set;
import likelion.hack6.member.domain.Gender;
import likelion.hack6.member.domain.Keyword;
import likelion.hack6.member.domain.Member;
import likelion.hack6.member.domain.Profile;
import likelion.hack6.member.domain.ProfileIcon;
import likelion.hack6.member.domain.StudentStatus;

public record SetupProfileCommand(
        String name,
        Gender gender,
        String major,
        int classOf,
        StudentStatus studentStatus,
        int grade,
        int age,
        ProfileIcon profileIcon,
        String brief,
        Set<Keyword> keywords
) {
    public Profile toProfile(Member member) {
        return new Profile(
                member.getProfile().getKakaoId(),
                member.getProfile().getUniversityEmail(),
                name,
                gender,
                major,
                classOf,
                studentStatus,
                grade,
                age,
                profileIcon,
                brief,
                keywords,
                true
        );
    }
}
