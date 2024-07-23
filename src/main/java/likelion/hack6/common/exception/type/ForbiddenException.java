package likelion.hack6.common.exception.type;

import likelion.hack6.common.exception.ApplicationException;

public class ForbiddenException extends ApplicationException {

    public ForbiddenException(String message) {
        super(message, 403);
    }
}
