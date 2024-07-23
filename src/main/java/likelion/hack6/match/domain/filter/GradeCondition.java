package likelion.hack6.match.domain.filter;

public record GradeCondition(
        int minGrade,
        int maxGrade
) {
    public boolean satisfy(int grade) {
        return minGrade <= grade && grade <= maxGrade;
    }
}
