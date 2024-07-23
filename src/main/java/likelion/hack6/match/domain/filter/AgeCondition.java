package likelion.hack6.match.domain.filter;

public record AgeCondition(
        Integer minAge,
        Integer maxAge
) {
    public boolean satisfy(int age) {
        return minAge <= age && age <= maxAge;
    }
}
