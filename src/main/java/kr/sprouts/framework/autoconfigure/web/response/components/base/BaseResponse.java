package kr.sprouts.framework.autoconfigure.web.response.components.base;

import kr.sprouts.framework.autoconfigure.web.response.components.body.link.Link;
import kr.sprouts.framework.autoconfigure.web.response.components.body.link.ReferenceLink;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BaseResponse {
    private HashMap<String, Link> links;
    private UUID id;

    protected BaseResponse(UUID id) {
        this.id = id;
        this.links = null;
    }

    private void initInnerLinks() {
        if (this.links == null) {
            this.links = new HashMap<>();
        }
    }

    private void addInnerLink(ReferenceLink link) {
        this.links.put(
                link.getDescription(),
                Link.fromReferenceLink(link)
        );
    }

    public BaseResponse addLink(ReferenceLink link) {
        this.initInnerLinks();
        this.addInnerLink(link);

        return this;
    }

    public BaseResponse addLinks(ReferenceLink ...links) {
        this.initInnerLinks();

        for (ReferenceLink link: links) {
            this.addInnerLink(link);
        }

        return this;
    }
}
