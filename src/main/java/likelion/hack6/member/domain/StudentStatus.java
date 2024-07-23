package likelion.hack6.member.domain;

public enum StudentStatus {

    CURRENT("재학생"),
    LEAVE_OF_ABSENCE("휴학생"),
    GRADUATION("졸업생");

    private final String status;

    StudentStatus(String status) {
        this.status = status;
    }
}
