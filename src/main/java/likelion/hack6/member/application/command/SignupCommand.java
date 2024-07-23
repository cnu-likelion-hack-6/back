package likelion.hack6.member.application.command;


import likelion.hack6.member.domain.Member;

public record SignupCommand(
        String phone,
        String password
) {
    public Member toMember() {
        return new Member(
                phone,
                password
        );
    }
}
