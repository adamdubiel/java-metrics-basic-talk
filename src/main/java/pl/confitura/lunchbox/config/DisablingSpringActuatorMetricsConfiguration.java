package pl.confitura.lunchbox.config;

import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricsDropwizardAutoConfiguration;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AutoConfigureBefore({MetricRepositoryAutoConfiguration.class, MetricsDropwizardAutoConfiguration.class})
public class DisablingSpringActuatorMetricsConfiguration {

    @Bean
    public CounterService counterService() {
        return new CounterService() {
            @Override
            public void increment(String metricName) {
            }

            @Override
            public void decrement(String metricName) {
            }

            @Override
            public void reset(String metricName) {
            }
        };
    }

    @Bean
    public GaugeService gaugeService() {
        return (String metricName, double value) -> {
        };
    }

}
