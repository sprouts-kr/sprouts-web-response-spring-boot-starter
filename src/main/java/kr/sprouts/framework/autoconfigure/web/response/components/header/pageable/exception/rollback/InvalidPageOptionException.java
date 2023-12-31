package kr.sprouts.framework.autoconfigure.web.response.components.header.pageable.exception.rollback;

import kr.sprouts.framework.autoconfigure.web.response.components.base.BaseRollbackException;
import org.springframework.http.HttpStatus;

public class InvalidPageOptionException extends BaseRollbackException {
    private InvalidPageOptionException(String reason) {
        super("invalid_page_option", reason, HttpStatus.BAD_REQUEST);
    }

    public static InvalidPageOptionException byValue() {
        return new InvalidPageOptionException("Page option value should be integer.");
    }
}
