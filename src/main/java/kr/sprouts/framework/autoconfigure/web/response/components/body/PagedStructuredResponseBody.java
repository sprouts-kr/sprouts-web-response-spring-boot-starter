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
public class PagedStructuredResponseBody<C extends Collection<? extends BaseResponse>> extends StructuredResponseBody {
    private C content;
    private Integer size;
    private Long totalSize;

    private PagedStructuredResponseBody(C content, Long totalSize) {
        super(true);
        this.content = content;
        this.size = (content == null) ? 0 : content.size();
        this.totalSize = totalSize;
    }

    public static <C extends Collection<? extends BaseResponse>> PagedStructuredResponseBody<C> of(C content, Long totalSize) {
        return new PagedStructuredResponseBody<>(content, totalSize);
    }
}
