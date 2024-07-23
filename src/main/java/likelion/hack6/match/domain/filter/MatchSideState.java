package likelion.hack6.match.domain.filter;

public enum MatchSideState {
    BUYER,  // 사줄래요
    TAKER,  // 먹을래요
    BOTH,
    ;  // 둘 다 좋아요

    public boolean satisfy(MatchSideState other) {
        // 둘 다 좋은 경우
        if (this == BOTH || other == BOTH) {
            return true;
        }

        // 위 조건문으로 인해, BOTH 는 아무도 없음
        return this != other;
    }
}
