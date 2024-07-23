package likelion.hack6.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.crypto.SecretKey;
import likelion.hack6.common.exception.type.UnAuthorizedException;
import org.springframework.stereotype.Component;

@Component
public class TokenService {

    private static final String MEMBER_ID_CLAIM = "memberId";

    private final SecretKey secretKey;
    private final long accessTokenExpirationDays;

    public TokenService(
            TokenProperty tokenProperty
    ) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(tokenProperty.secretKey()));
        this.accessTokenExpirationDays = tokenProperty.accessTokenExpirationDays();
    }

    public TokenResponse createToken(Long memberId) {
        String accessToken = createAccessToken(memberId);
        return new TokenResponse(accessToken);
    }

    private String createAccessToken(Long memberId) {
        return Jwts.builder()
                .claim(MEMBER_ID_CLAIM, memberId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(accessTokenExpirationDays)))
                .signWith(secretKey, SIG.HS512)
                .compact();
    }

    public Long extractMemberId(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get(MEMBER_ID_CLAIM, Long.class);
        } catch (Exception e) {
            throw new UnAuthorizedException("유효하지 않은 토큰입니다.");
        }
    }
}
