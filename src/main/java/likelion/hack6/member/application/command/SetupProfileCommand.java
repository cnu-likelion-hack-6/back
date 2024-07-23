package likelion.hack6.member.application.command;

import likelion.hack6.member.domain.Keyword;
import likelion.hack6.member.domain.Member;
import likelion.hack6.member.domain.Profile;
import likelion.hack6.member.domain.ProfileIcon;
import likelion.hack6.member.domain.StudentStatus;

public record SetupProfileCommand(
        String name,
        String major,
        int classOf,
        StudentStatus studentStatus,
        int grade,
        int age,
        ProfileIcon profileIcon,
        String brief,
        Keyword keyword
) {
    public Profile toProfile(Member member) {
        return new Profile(
                member.getProfile().getKakaoId(),
                member.getProfile().getUniversityEmail(),
                name,
                major,
                classOf,
                studentStatus,
                grade,
                age,
                profileIcon,
                brief,
                keyword,
                true
        );
    }
}
