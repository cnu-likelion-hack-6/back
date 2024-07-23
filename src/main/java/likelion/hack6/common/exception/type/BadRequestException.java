package likelion.hack6.common.exception.type;

import likelion.hack6.common.exception.ApplicationException;

public class BadRequestException extends ApplicationException {

    public BadRequestException(String message) {
        super(message, 400);
    }
}
