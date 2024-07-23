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
    private DepartmentsCondition departmentsCondition;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private MatchSideState matchSideState;

    public Filter(
            Member member,
            AgeCondition ageCondition,
            GenderCondition genderCondition,
            GradeCondition gradeCondition,
            DepartmentsCondition departmentsCondition,
            MatchSideState matchSideState
    ) {
        this.member = member;
        this.ageCondition = ageCondition;
        this.genderCondition = genderCondition;
        this.gradeCondition = gradeCondition;
        this.departmentsCondition = departmentsCondition;
        this.matchSideState = matchSideState;
    }

    public void updateMatchSide(MatchSideState matchSideState) {
        this.matchSideState = matchSideState;
    }
}
