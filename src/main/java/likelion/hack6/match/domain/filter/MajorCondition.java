package likelion.hack6.match.domain.filter;

import org.apache.commons.lang3.StringUtils;

public enum MajorCondition {
    ONLY_SAME,
    BOTH;

    public boolean satisfy(String myMajor, String otherMajor) {
        if (this == BOTH) {
            return true;
        }
        return StringUtils.equalsIgnoreCase(
                myMajor.replaceAll("\\s", ""),
                otherMajor.replaceAll("\\s", "")
        );
    }
}
