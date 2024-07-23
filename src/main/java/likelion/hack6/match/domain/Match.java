package likelion.hack6.match.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import likelion.hack6.common.domain.RootEntity;
import likelion.hack6.common.exception.type.ForbiddenException;
import likelion.hack6.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Match extends RootEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private Member buyer;  // 사주는 사람

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Member taker;  // 얻어먹는 사람

    private String thanksMessageToBuyer;

    private String thanksMessageToTaker;

    public Match(Member buyer, Member taker) {
        this.buyer = buyer;
        this.taker = taker;
    }

    public void writeMessage(Member member, String message) {
        if (buyer.equals(member)) {
            this.thanksMessageToTaker = message;
            return;
        }
        if (taker.equals(member)) {
            this.thanksMessageToBuyer = message;
            return;
        }
        throw new ForbiddenException("해당 Match 에 대한 권한이 없습니다.");
    }
}
