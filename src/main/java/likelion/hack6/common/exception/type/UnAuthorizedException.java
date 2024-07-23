package likelion.hack6.common.exception.type;

import likelion.hack6.common.exception.ApplicationException;

public class UnAuthorizedException extends ApplicationException {

    public UnAuthorizedException(String message) {
        super(message, 401);
    }
}
