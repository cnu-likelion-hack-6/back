package likelion.hack6.match.domain.filter;

public record GradeCondition(
        Integer minGrade,
        Integer maxGrade
) {
    public boolean satisfy(int grade) {
        return minGrade <= grade && grade <= maxGrade;
    }
}
