package co.com.ias.certification.backend.configuration.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
@RequiredArgsConstructor
@Data
public class ElasticSearchCredentials {
    private String host;
    private Integer port;
}
