package kr.sprouts.framework.autoconfigure.web.response.components.entity;

import kr.sprouts.framework.autoconfigure.web.response.components.base.BaseResponse;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collection;

public class StructuredPagedResponseEntity<C extends Collection<? extends BaseResponse>> {
    @Getter
    private static final HttpStatus httpStatus = HttpStatus.OK;
    @Getter(AccessLevel.PACKAGE)
    private final C baseResponseBody;

    private StructuredPagedResponseEntity(C baseResponses) {
        this.baseResponseBody = baseResponses;
    }

    static <C extends Collection<? extends BaseResponse>> StructuredPagedResponseEntity<C> createFromMultiResponse(C responses) {
        return new StructuredPagedResponseEntity<>(responses);
    }

    public StructuredResponseEntity withTotalSize(Long totalSize) {
        return StructuredResponseEntity.createFromStructuredPagedResponseEntity(
                this,
                totalSize
        );
    }
}
