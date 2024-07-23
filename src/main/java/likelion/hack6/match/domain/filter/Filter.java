package likelion.hack6.match.domain.filter;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import likelion.hack6.common.domain.RootEntity;
import likelion.hack6.member.domain.Gender;
import likelion.hack6.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Filter extends RootEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private AgeCondition ageCondition;

    @Enumerated(EnumType.STRING)
    private GenderCondition genderCondition;

    @Embedded
    private GradeCondition gradeCondition;

    @Enumerated(EnumType.STRING)
    private MajorCondition majorCondition;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private MatchSideState matchSideState;

    public Filter(
            Member member,
            AgeCondition ageCondition,
            GenderCondition genderCondition,
            GradeCondition gradeCondition,
            MajorCondition majorCondition,
            MatchSideState matchSideState
    ) {
        this.member = member;
        this.ageCondition = ageCondition;
        this.genderCondition = genderCondition;
        this.gradeCondition = gradeCondition;
        this.majorCondition = majorCondition;
        this.matchSideState = matchSideState;
    }

    public void updateMatchSide(MatchSideState matchSideState) {
        this.matchSideState = matchSideState;
    }

    public boolean matchable(Filter otherFilter) {
        if (!satisfyAgeCond(otherFilter)) {
            return false;
        }
        if (!satisfyGenderCond(otherFilter)) {
            return false;
        }
        if (!satisfyGradeCond(otherFilter)) {
            return false;
        }
        if (satisfyMajorCond(otherFilter)) {
            return false;
        }
        if (satisfySideCond(otherFilter)) {
            return false;
        }
        return true;
    }

    private boolean satisfyAgeCond(Filter otherFilter) {
        int myAge = member.getProfile().getAge();
        int otherAge = otherFilter.getMember().getProfile().getAge();
        return ageCondition.satisfy(otherAge) && otherFilter.ageCondition.satisfy(myAge);
    }

    private boolean satisfyGenderCond(Filter otherFilter) {
        Gender myGender = member.getProfile().getGender();
        Gender otherGender = otherFilter.getMember().getProfile().getGender();
        return genderCondition.satisfy(myGender, otherGender)
               && otherFilter.genderCondition.satisfy(otherGender, myGender);
    }

    private boolean satisfyGradeCond(Filter otherFilter) {
        int myGrade = member.getProfile().getGrade();
        int otherGrade = otherFilter.getMember().getProfile().getGrade();
        return gradeCondition.satisfy(otherGrade) && otherFilter.gradeCondition.satisfy(myGrade);
    }

    private boolean satisfyMajorCond(Filter otherFilter) {
        String myMajor = member.getProfile().getMajor();
        String otherMajor = otherFilter.getMember().getProfile().getMajor();
        return majorCondition.satisfy(myMajor, otherMajor)
               && otherFilter.majorCondition.satisfy(otherMajor, myMajor);
    }

    private boolean satisfySideCond(Filter otherFilter) {
        return matchSideState.satisfy(otherFilter.matchSideState);
    }
}
