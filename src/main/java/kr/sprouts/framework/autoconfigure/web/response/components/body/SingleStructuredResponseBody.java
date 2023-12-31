package kr.sprouts.framework.autoconfigure.web.response.components.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.sprouts.framework.autoconfigure.web.response.components.base.BaseResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class SingleStructuredResponseBody<T extends BaseResponse> extends StructuredResponseBody {
    private T content;

    private SingleStructuredResponseBody(T content) {
        super(true);
        this.content = content;
    }

    public static <T extends BaseResponse> SingleStructuredResponseBody<T> of(T content) {
        return new SingleStructuredResponseBody<>(content);
    }
}
