package pl.confitura.lunchbox.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pl.confitura.lunchbox.infrastructure.refresh.ZomatoProperties;

@Configuration
@EnableConfigurationProperties(ZomatoProperties.class)
public class LunchboxConfiguration {
}
