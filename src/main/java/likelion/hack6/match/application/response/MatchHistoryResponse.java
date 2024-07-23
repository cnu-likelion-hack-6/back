package likelion.hack6.match.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Set;
import likelion.hack6.match.domain.Match;
import likelion.hack6.member.domain.Keyword;
import likelion.hack6.member.domain.Member;
import likelion.hack6.member.domain.Profile;
import likelion.hack6.member.domain.ProfileIcon;

public record MatchHistoryResponse(
        @Schema(description = "사준 사람 정보")
        MatchedMemberInfo buyerInfo,

        @Schema(description = "얻어먹은 사람 정보")
        MatchedMemberInfo takerInfo,

        @Schema(description = "매칭된 날짜")
        LocalDateTime matchedDate
) {

    public static MatchHistoryResponse from(Match match) {
        return new MatchHistoryResponse(
                MatchedMemberInfo.from(match.getBuyer()),
                MatchedMemberInfo.from(match.getTaker()),
                match.getCreatedDate()
        );
    }

    public record MatchedMemberInfo(
            Long memberId,

            @Schema(description = "카톡 ID")
            String kakaoId,

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
        public static MatchedMemberInfo from(Member member) {
            Profile profile = member.getProfile();
            return new MatchedMemberInfo(
                    member.getId(),
                    profile.getKakaoId(),
                    profile.getProfileIcon(),
                    profile.getName(),
                    profile.getUniversityEmail().getUniversity().getUniversityName(),
                    profile.getMajor(),
                    profile.getGrade(),
                    profile.getClassOf(),
                    profile.getAge(),
                    profile.getBrief(),
                    profile.getKeywords()
            );
        }
    }
}
