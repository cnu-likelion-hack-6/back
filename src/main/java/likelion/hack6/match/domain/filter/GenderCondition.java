package likelion.hack6.match.domain.filter;

import likelion.hack6.member.domain.Gender;

public enum GenderCondition {
    ONLY_SAME,
    BOTH;

    public boolean satisfy(Gender myGender, Gender otherGender) {
        if (this == BOTH) {
            return true;
        }
        return myGender.equals(otherGender);
    }
}
