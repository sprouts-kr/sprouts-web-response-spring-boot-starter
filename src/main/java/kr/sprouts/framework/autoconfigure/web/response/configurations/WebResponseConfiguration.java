package kr.sprouts.framework.autoconfigure.web.response.configurations;

import kr.sprouts.framework.autoconfigure.web.response.components.body.link.LinkBuilder;
import kr.sprouts.framework.autoconfigure.web.response.components.entity.StructuredResponse;
import kr.sprouts.framework.autoconfigure.web.response.properties.WebResponseConfigurationProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import java.net.MalformedURLException;
import java.net.URL;

@AutoConfiguration
@EnableConfigurationProperties(value = { WebResponseConfigurationProperty.class })
@ComponentScan(basePackageClasses = { LinkBuilder.class, StructuredResponse.class })
@Slf4j
public class WebResponseConfiguration {
    private final WebResponseConfigurationProperty webResponseConfigurationProperty;

    public WebResponseConfiguration(WebResponseConfigurationProperty webResponseConfigurationProperty) {
        this.webResponseConfigurationProperty = webResponseConfigurationProperty;

        if (StringUtils.isBlank(this.webResponseConfigurationProperty.getDefaultHost())) {
            throw new IllegalArgumentException("Property sprouts.web.response.default-host is required.");
        }

        if (this.webResponseConfigurationProperty.getHosts() == null || webResponseConfigurationProperty.getHosts().isEmpty()) {
            throw new IllegalArgumentException("Property sprouts.web.response.hosts is required.");
        }

        if (this.webResponseConfigurationProperty.getHosts().stream().filter(host -> StringUtils.isNotBlank(host.getName()) && StringUtils.isNotBlank(host.getUrl()) && this.isValidUrl(host.getUrl())).count() != webResponseConfigurationProperty.getHosts().size()) {
            throw new IllegalArgumentException("Property sprouts.web.response.hosts has an invalid host defined.");
        }

        if (this.webResponseConfigurationProperty.getHosts().stream().noneMatch(host -> this.webResponseConfigurationProperty.getDefaultHost().equals(host.getName()))) {
            throw new IllegalArgumentException("No match for sprouts.web.response.default-host in sprouts.web.response.hosts");
        }

        if (log.isInfoEnabled()) log.info("Initialized {}", WebResponseConfiguration.class.getSimpleName());
    }

    private boolean isValidUrl(String url) {
        try {
            new URL(url);
            return true;
        }
        catch (MalformedURLException e) {
            return false;
        }
    }
}
