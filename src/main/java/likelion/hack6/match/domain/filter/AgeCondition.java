package likelion.hack6.match.domain.filter;

public record AgeCondition(
        int minAge,
        int maxAge
) {
    public boolean satisfy(int age) {
        return minAge <= age && age <= maxAge;
    }
}
