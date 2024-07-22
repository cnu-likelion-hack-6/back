package likelion.hack6.auth.jwt;


import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

//왜 안되는지 모르겠음요 의존성을 추가해야된다는데 대체 어디에,,,?
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import likelion.hack6.common.UnAuthorizedException;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtService {

    private final long accessTokenExpirationDayToMills;
    private final Algorithm algorithm;

    public JwtService(JwtProperty jwtProperty) {
        this.accessTokenExpirationDayToMills =
                MILLISECONDS.convert(jwtProperty.accessTokenExpirationDay(), DAYS);
        this.algorithm = Algorithm.HMAC512(jwtProperty.secretKey());
    }

    public String createToken(Long memberId) {
        return JWT.create()
                .withExpiresAt(new Date(
                        accessTokenExpirationDayToMills + System.currentTimeMillis()
                ))
                .withIssuedAt(new Date())
                .withClaim("memberId", memberId)
                .sign(algorithm);
    }

    public Long extractMemberId(String token) {
        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getClaim("memberId")
                    .asLong();
        } catch (JWTVerificationException e) {
            throw new UnAuthorizedException("유효하지 않은 토큰입니다.");
        }
    }
}
