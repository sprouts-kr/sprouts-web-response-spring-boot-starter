package kr.sprouts.framework.autoconfigure.web.response.components.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.sprouts.framework.autoconfigure.web.response.components.base.BaseResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ComposedStructuredResponseBody<C extends Collection<? extends BaseResponse>> extends StructuredResponseBody {
    private C content;
    private Integer size;

    private ComposedStructuredResponseBody(C content) {
        super(true);
        this.content = content;
        this.size = (content == null) ? 0 : content.size();
    }

    public static <C extends Collection<? extends BaseResponse>> ComposedStructuredResponseBody<C> of(C content) {
        return new ComposedStructuredResponseBody<>(content);
    }
}
