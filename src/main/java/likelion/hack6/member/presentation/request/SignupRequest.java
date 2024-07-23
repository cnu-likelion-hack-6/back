package likelion.hack6.member.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import likelion.hack6.member.application.command.SignupCommand;

public record SignupRequest(
        @Schema(example = "01012341234")
        @NotBlank(message = "핸드폰번호는 필수 값입니다.") String phone,

        @Schema(example = "1234")
        @NotBlank(message = "비밀번호는 필수 값입니다.") String password
) {
    public SignupCommand toCommand() {
        return new SignupCommand(phone, password);
    }
}
