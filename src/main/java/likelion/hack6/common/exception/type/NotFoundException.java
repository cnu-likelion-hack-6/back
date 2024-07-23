package likelion.hack6.common.exception.type;

import likelion.hack6.common.exception.ApplicationException;

public class NotFoundException extends ApplicationException {

    public NotFoundException(String message) {
        super(message, 404);
    }
}
