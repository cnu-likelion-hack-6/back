package likelion.hack6.match.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import likelion.hack6.match.application.command.CreateFilterCommand;
import likelion.hack6.match.domain.filter.DepartmentCondition;
import likelion.hack6.match.domain.filter.GenderCondition;

public record CreateFilterRequest(
        @Schema(description = "최소 나이")
        @Positive int minAge,

        @Schema(description = "최대 나이")
        @Positive int maxAge,

        @Schema(description = "성별 필터")
        @NotNull GenderCondition genderCondition,

        @Schema(description = "최소 학년")
        @Positive int minGrade,

        @Schema(description = "최대 학년")
        @Positive int maxGrade,

        @Schema(description = "학과 필터")
        @NotNull DepartmentCondition departmentCondition
) {
    public CreateFilterCommand toCommand() {
        return new CreateFilterCommand(
                minAge,
                maxAge,
                genderCondition,
                minGrade,
                maxGrade,
                departmentCondition
        );
    }
}
