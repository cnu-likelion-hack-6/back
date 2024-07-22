package likelion.hack6.member.controller;

import likelion.hack6.auth.jwt.JwtService;
import likelion.hack6.member.domain.LoginRequest;
import likelion.hack6.member.domain.LoginResponse;
import likelion.hack6.member.domain.MemberResponse;
import likelion.hack6.member.domain.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final JwtService jwtService;

    //로그인
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody final LoginRequest loginRequest) {

        Long memberId = memberService.login(loginRequest.phone(), loginRequest.password());
        String accessToken = jwtService.createToken(memberId);
        return ResponseEntity.ok(new LoginResponse(accessToken));
    }

    //내 프로필
    @GetMapping("/my")
    public ResponseEntity<MemberResponse> getMyProfile(
             String phone
    ) {
        MemberResponse response = memberService.findByphone(phone);
        return ResponseEntity.ok(response);
    }

}
