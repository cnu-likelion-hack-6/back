package likelion.hack6.match.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import likelion.hack6.match.domain.filter.MatchSideState;
import likelion.hack6.member.domain.Keyword;
import likelion.hack6.member.domain.Member;
import likelion.hack6.member.domain.Profile;
import likelion.hack6.member.domain.ProfileIcon;

public record MatchableMemberResponse(
        Long memberId,

        @Schema(description = "사줄래요(BUYER), 먹을래요(TAKER), 상관없어요(BOTH)")
        MatchSideState side,

        @Schema(description = "프로필 아이콘")
        ProfileIcon profileIcon,

        @Schema(description = "이름")
        String name,

        @Schema(description = "대학교 이름")
        String universityName,

        @Schema(description = "전공")
        String major,

        @Schema(description = "학년")
        int grade,

        @Schema(description = "학번(ex: 19)")
        int classOf,

        @Schema(description = "나이")
        int age,

        @Schema(description = "한 줄 소개")
        String brief,

        @Schema(description = "관심 키워드들")
        Set<Keyword> keywords
) {
    public static MatchableMemberResponse of(Member member, MatchSideState side) {
        Profile profile = member.getProfile();
        return new MatchableMemberResponse(
                member.getId(),
                side,
                profile.getProfileIcon(),
                profile.getName(),
                profile.getUniversityEmail().getUniversity().getUniversityName(),
                profile.getDepartment(),
                profile.getGrade(),
                profile.getClassOf(),
                profile.getAge(),
                profile.getBrief(),
                profile.getKeywords()
        );
    }
}
