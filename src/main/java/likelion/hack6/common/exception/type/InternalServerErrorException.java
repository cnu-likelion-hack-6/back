package likelion.hack6.common.exception.type;

import likelion.hack6.common.exception.ApplicationException;

public class InternalServerErrorException extends ApplicationException {

    public InternalServerErrorException(String message) {
        super(message, 500);
    }
}
