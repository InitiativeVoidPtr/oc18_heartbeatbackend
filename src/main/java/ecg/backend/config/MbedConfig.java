package ecg.backend.config;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Brief description
 * <p>
 * Detailed description
 * <p>
 * <p>
 *
 * @author mspoeri - Die Softwareklitsche GbR
 * @version 1.0
 */
@Configuration
public class MbedConfig {

    public static final String API_KEY    = "PUSRP6L4BIpQnkavaFmzou3hM7tNVLMFSC9UhAAuR2lqcWYyxVnJBetdi0M7j1JkBrAZ0natm15NUgjRVLPuYmqzMkg6fV5d9pOC";
    public static final  String API_ACCESS = "https://api.connector.mbed.com";

    @Bean
    @Qualifier("mbedTemplateBuilder")
    public RestTemplateBuilder getMbedRestTemplateBuilder() {
        final RestTemplateBuilder restTemplateBuilder;

        restTemplateBuilder = new RestTemplateBuilder(r -> r.setInterceptors(Collections.singletonList((request, body, execution) -> {
            request.getHeaders().set(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY);
            return execution.execute(request, body);
        })));

        return restTemplateBuilder;
    }

    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
                        .baseUrl(API_ACCESS)
                        .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY).build();
    }
}
