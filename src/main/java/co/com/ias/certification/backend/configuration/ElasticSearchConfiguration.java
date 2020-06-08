package co.com.ias.certification.backend.configuration;

import co.com.ias.certification.backend.configuration.domain.ElasticSearchCredentials;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ElasticSearchConfiguration {

    @Bean
    @Profile({"dev", "prod"})
    public RestHighLevelClient elasticSearchClient(ElasticSearchCredentials elasticSearchCredentials) {
        HttpHost host = new HttpHost(elasticSearchCredentials.getHost(), elasticSearchCredentials.getPort(), "http");
        return new RestHighLevelClient(RestClient.builder(host));
    }
}
