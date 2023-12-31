package kr.sprouts.framework.autoconfigure.web.response.components.body;

import kr.sprouts.framework.autoconfigure.web.response.components.body.link.Link;
import kr.sprouts.framework.autoconfigure.web.response.components.body.link.ReferenceLink;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StructuredResponseBody {
    private boolean succeeded;
    private HashMap<String, Link> links;

    protected StructuredResponseBody(boolean succeeded) {
        this.succeeded = succeeded;
        this.links = null;
    }

    private void initInnerLink() {
        if (this.links == null) {
            this.links = new HashMap<>();
        }
    }

    private void addInnerLink(ReferenceLink referenceLink) {
        this.links.put(
                referenceLink.getDescription(),
                Link.fromReferenceLink(referenceLink)
        );
    }

    public StructuredResponseBody addLink(ReferenceLink referenceLink) {
        this.initInnerLink();
        this.addInnerLink(referenceLink);
        return this;
    }

    public StructuredResponseBody addLinks(ReferenceLink... referenceLinks) {
        this.initInnerLink();

        for (ReferenceLink referenceLink: referenceLinks) {
            this.addInnerLink(referenceLink);
        }

        return this;
    }
}
