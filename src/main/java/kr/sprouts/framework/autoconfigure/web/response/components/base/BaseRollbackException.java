package kr.sprouts.framework.autoconfigure.web.response.components.base;

import org.springframework.http.HttpStatus;

public class BaseRollbackException extends BaseException {
    public BaseRollbackException(String value, String reason, HttpStatus httpStatus) {
        super(value, reason, httpStatus);
    }
}
