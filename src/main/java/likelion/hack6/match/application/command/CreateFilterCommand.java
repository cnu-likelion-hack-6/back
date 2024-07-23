package likelion.hack6.match.application.command;

import likelion.hack6.match.domain.filter.AgeCondition;
import likelion.hack6.match.domain.filter.Filter;
import likelion.hack6.match.domain.filter.GenderCondition;
import likelion.hack6.match.domain.filter.GradeCondition;
import likelion.hack6.match.domain.filter.MajorCondition;
import likelion.hack6.member.domain.Member;

public record CreateFilterCommand(
        Integer minAge,
        Integer maxAge,
        GenderCondition genderCondition,
        Integer minGrade,
        Integer maxGrade,
        MajorCondition majorCondition
) {
    public Filter toFilter(Member member) {
        return new Filter(
                member,
                new AgeCondition(minAge, maxAge),
                genderCondition,
                new GradeCondition(minGrade, maxGrade),
                majorCondition,
                null
        );
    }
}