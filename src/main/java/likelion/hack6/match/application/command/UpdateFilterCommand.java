package likelion.hack6.match.application.command;

import likelion.hack6.match.domain.filter.AgeCondition;
import likelion.hack6.match.domain.filter.GenderCondition;
import likelion.hack6.match.domain.filter.GradeCondition;
import likelion.hack6.match.domain.filter.MajorCondition;

public record UpdateFilterCommand(
        int minAge,
        int maxAge,
        GenderCondition genderCondition,
        int minGrade,
        int maxGrade,
        MajorCondition majorCondition
) {
    public AgeCondition ageCondition() {
        return new AgeCondition(minAge, maxAge);
    }

    public GradeCondition gradeCondition() {
        return new GradeCondition(minGrade, maxGrade);
    }
}
