package likelion.hack6.member.presentation.response;

import likelion.hack6.member.domain.Member;
import likelion.hack6.member.domain.Profile;
import likelion.hack6.member.domain.ProfileIcon;
import likelion.hack6.member.domain.StudentStatus;

public record MemberResponse(
        Long memberId,
        String name,
        String university,
        String universityEmail,
        Integer classOf, // 학번 (ex: 19학번)
        StudentStatus studentStatus,  // 상태
        Integer grade,  // 학년
        Integer age,  // 나이
        ProfileIcon profileIcon,
        String brief  // 한 줄 소개
) {
    public static MemberResponse from(Member member) {
        Profile profile = member.getProfile();
        return new MemberResponse(
                member.getId(),
                profile.getName(),
                profile.getUniversityEmail().getUniversity().getUniversityName(),
                profile.getUniversityEmail().getEmail(),
                profile.getClassOf(),
                profile.getStudentStatus(),
                profile.getGrade(),
                profile.getAge(),
                profile.getProfileIcon(),
                profile.getBrief()
        );
    }
}
