package likelion.hack6.common.exception.type;

import likelion.hack6.common.exception.ApplicationException;

public class ConflictException extends ApplicationException {

    public ConflictException(String message) {
        super(message, 409);
    }
}
