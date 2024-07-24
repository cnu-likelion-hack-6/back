package likelion.hack6.match.application.command;

import likelion.hack6.match.domain.filter.AgeCondition;
import likelion.hack6.match.domain.filter.DepartmentCondition;
import likelion.hack6.match.domain.filter.GenderCondition;
import likelion.hack6.match.domain.filter.GradeCondition;

public record UpdateFilterCommand(
        int minAge,
        int maxAge,
        GenderCondition genderCondition,
        int minGrade,
        int maxGrade,
        DepartmentCondition departmentCondition
) {
    public AgeCondition ageCondition() {
        return new AgeCondition(minAge, maxAge);
    }

    public GradeCondition gradeCondition() {
        return new GradeCondition(minGrade, maxGrade);
    }
}
