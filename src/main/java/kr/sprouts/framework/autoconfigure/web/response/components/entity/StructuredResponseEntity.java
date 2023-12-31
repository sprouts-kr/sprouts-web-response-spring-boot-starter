package kr.sprouts.framework.autoconfigure.web.response.components.entity;

import kr.sprouts.framework.autoconfigure.web.response.components.base.BaseException;
import kr.sprouts.framework.autoconfigure.web.response.components.base.BaseResponse;
import kr.sprouts.framework.autoconfigure.web.response.components.body.ComposedStructuredResponseBody;
import kr.sprouts.framework.autoconfigure.web.response.components.body.ExceptionStructuredResponseBody;
import kr.sprouts.framework.autoconfigure.web.response.components.body.PagedStructuredResponseBody;
import kr.sprouts.framework.autoconfigure.web.response.components.body.SingleStructuredResponseBody;
import kr.sprouts.framework.autoconfigure.web.response.components.body.StructuredResponseBody;
import kr.sprouts.framework.autoconfigure.web.response.components.body.link.ReferenceLink;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Collection;

public class StructuredResponseEntity extends ResponseEntity<StructuredResponseBody> {
    private StructuredResponseEntity(HttpStatus httpStatus) {
        super(httpStatus);
    }

    private StructuredResponseEntity(StructuredResponseBody body, HttpStatus httpStatus) {
        super(body, httpStatus);
    }

    private StructuredResponseEntity(StructuredResponseBody body, HttpStatus httpStatus, MultiValueMap<String, String> headers) {
        super(body, headers, httpStatus);
    }

    static StructuredResponseEntity createFromHttpStatus(HttpStatus httpStatus) {
        return new StructuredResponseEntity(httpStatus);
    }

    static <E extends BaseException> StructuredResponseEntity createFromExceptionResponse(E exception) {
        return new StructuredResponseEntity(
                ExceptionStructuredResponseBody.of(exception), exception.getHttpStatus()
        );
    }

    static <T extends BaseResponse> StructuredResponseEntity createFromSingleResponse(T response, HttpStatus httpStatus) {
        return new StructuredResponseEntity(
                SingleStructuredResponseBody.of(response), httpStatus
        );
    }

    static <C extends Collection<? extends BaseResponse>> StructuredResponseEntity createFromMultiResponse(C response, HttpStatus httpStatus) {
        return new StructuredResponseEntity(
                ComposedStructuredResponseBody.of(response), httpStatus
        );
    }

    static StructuredResponseEntity createFromStructuredPagedResponseEntity(StructuredPagedResponseEntity<?> structuredPagedResponseEntity, Long totalSize) {
        return new StructuredResponseEntity(
                PagedStructuredResponseBody.of(
                        structuredPagedResponseEntity.getBaseResponseBody(),
                        totalSize
                ),
                StructuredPagedResponseEntity.getHttpStatus()
        );
    }

    static StructuredResponseEntity createFromStructuredCreateResponseEntity(StructuredCreateResponseEntity structuredCreateResponseEntity) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(structuredCreateResponseEntity.getLocation());

        return new StructuredResponseEntity(
                SingleStructuredResponseBody.of(
                        structuredCreateResponseEntity.getBaseResponseBody()
                ),
                StructuredCreateResponseEntity.getHttpStatus(),
                httpHeaders
        );
    }

    public StructuredResponseEntity addLink(ReferenceLink link) {
        if (this.getBody() != null) {
            this.getBody().addLink(link);
        }

        return this;
    }

    public StructuredResponseEntity addLinks(ReferenceLink ...links) {
        if (this.getBody() != null) {
            this.getBody().addLinks(links);
        }

        return this;
    }
}
