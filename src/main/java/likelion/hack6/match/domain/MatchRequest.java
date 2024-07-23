package likelion.hack6.match.domain;

import static likelion.hack6.match.domain.filter.MatchSideState.BOTH;
import static likelion.hack6.match.domain.filter.MatchSideState.TAKER;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import likelion.hack6.common.domain.RootEntity;
import likelion.hack6.match.domain.filter.Filter;
import likelion.hack6.match.domain.filter.MatchSideState;
import likelion.hack6.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MatchRequest extends RootEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id")
    private Member requester;

    @Enumerated(EnumType.STRING)
    private MatchSideState requesterSide;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Member receiver;

    @Enumerated(EnumType.STRING)
    private MatchSideState receiverSide;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    public MatchRequest(Filter requesterFilter, Filter receiverFilter) {
        this.requesterSide = requesterFilter.getMatchSideState();
        this.requester = requesterFilter.getMember();
        this.receiverSide = receiverFilter.getMatchSideState();
        this.receiver = receiverFilter.getMember();
    }

    public Match accept() {
        this.status = RequestStatus.ACCEPTED;

        // 둘 다 상관없을 -> 요청자가 사줘라!
        if (requesterSide == BOTH || receiverSide == BOTH) {
            return new Match(requester, receiver);
        }

        // 요청자가 상관없을 때
        if (requesterSide == BOTH) {
            // 수락자가 '얻어먹을래요' 라면 -> [요청자 : 사주는 사람], [수락자 - 얻어먹는 사람]
            if (receiverSide == TAKER) {
                return new Match(requester, receiver);
            }

            // 수락자가 '사줄래요' 라면 -> [요청자 : 얻어먹는 사람], [수락자 - 사주는 사람]
            if (receiverSide == TAKER) {
                return new Match(receiver, requester);
            }
        }

        // 수락자가 상관없을 때
        if (receiverSide == BOTH) {
            // 요청자가 '얻어먹을래요' 라면 -> [요청자 : 얻어먹는 사람], [수락자 - 사주는 사람]
            if (requesterSide == TAKER) {
                return new Match(receiver, requester);
            }

            // 요청자가 '사줄래요' 라면 -> [요청자 : 사주는 사람], [수락자 - 얻어먹는 사람]
            if (requesterSide == TAKER) {
                return new Match(requester, receiver);
            }
        }

        // 수락자가 얻어먹을래요
        if (receiverSide == TAKER) {
            return new Match(requester, receiver);
        }

        // 이외 경우 (수락자가 사줄래요, 요청자)
        return new Match(receiver, requester);
    }

    public void reject() {
        this.status = RequestStatus.REJECTED;
    }
}
