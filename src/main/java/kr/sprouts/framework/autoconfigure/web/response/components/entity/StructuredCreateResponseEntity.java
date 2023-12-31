package kr.sprouts.framework.autoconfigure.web.response.components.entity;

import jakarta.validation.constraints.NotNull;
import kr.sprouts.framework.autoconfigure.web.response.components.base.BaseResponse;
import kr.sprouts.framework.autoconfigure.web.response.components.entity.function.CreatedLocationFunction;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.net.URI;

public class StructuredCreateResponseEntity {
    @Getter
    private static final HttpStatus httpStatus = HttpStatus.CREATED;
    @Getter
    private URI location;
    @Getter(AccessLevel.PACKAGE)
    private final BaseResponse baseResponseBody;

    private StructuredCreateResponseEntity(BaseResponse baseResponse) {
        this.baseResponseBody = baseResponse;
    }

    static <T extends BaseResponse> StructuredCreateResponseEntity createFromSingleResponse(T response) {
        return new StructuredCreateResponseEntity(response);
    }

    public StructuredResponseEntity withLocation(@NotNull CreatedLocationFunction createdLocationFunction) {
        this.location = createdLocationFunction.apply(this.getBaseResponseBody());

        return StructuredResponseEntity.createFromStructuredCreateResponseEntity(
                this
        );
    }
}
