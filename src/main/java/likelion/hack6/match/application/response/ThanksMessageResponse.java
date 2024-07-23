package likelion.hack6.match.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import likelion.hack6.match.domain.Match;
import likelion.hack6.member.domain.Member;
import likelion.hack6.member.domain.Profile;
import likelion.hack6.member.domain.ProfileIcon;

public record ThanksMessageResponse(
        Long memberId,

        LocalDateTime matchedDate,

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

        @Schema(description = "감사인사 메세지")
        String message
) {

    public static ThanksMessageResponse of(Match match, Member member) {
        Member target = match.getOtherSide(member);
        Profile profile = target.getProfile();
        return new ThanksMessageResponse(
                target.getId(),
                match.getCreatedDate(),
                profile.getProfileIcon(),
                profile.getName(),
                profile.getUniversityEmail().getUniversity().getUniversityName(),
                profile.getMajor(),
                profile.getGrade(),
                profile.getClassOf(),
                match.getGivenMessage(member)
        );
    }
}
