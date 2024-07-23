package likelion.hack6.member.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Set;
import likelion.hack6.member.application.command.SetProfileCommand;
import likelion.hack6.member.domain.Gender;
import likelion.hack6.member.domain.Keyword;
import likelion.hack6.member.domain.ProfileIcon;
import likelion.hack6.member.domain.StudentStatus;

public record SetProfileRequest(
        @Schema(description = "이름")
        @NotBlank String name,

        @Schema(description = "성별")
        @NotNull Gender gender,

        @Schema(description = "전공")
        @NotBlank String major,

        @Schema(description = "학번 (ex: 19)")
        @Positive int classOf,

        @Schema(description = "상태 (재학생(CURRENT), 휴학생(LEAVE_OF_ABSENCE), 졸업생(GRADUATION))")
        @NotNull StudentStatus studentStatus,

        @Schema(description = "학년")
        @Positive int grade,

        @Schema(description = "나이")
        @Positive int age,

        @Schema(description = "프로필 아이콘 번호 (ICON_1 ~ ICON_9)")
        @NotNull ProfileIcon profileIcon,

        @Schema(description = "한 줄 소개")
        @NotBlank String brief,

        @Schema(description = "관심 키워드들 (없으면 \"\", 여러개면 , 로 구분", example = "CAREERS, EMPLOYMENT")
        Set<Keyword> keywords
) {
    public SetProfileCommand toCommand() {
        return new SetProfileCommand(
                name,
                gender,
                major,
                classOf,
                studentStatus,
                grade,
                age,
                profileIcon,
                brief,
                keywords
        );
    }
}
