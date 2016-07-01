package pl.confitura.lunchbox.config;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

@Configuration
public class MetricsConfiguration {

    @Bean
    public MetricRegistry metricRegistry() {
        return new MetricRegistry();
    }

    @Configuration
    public static class MetricsGraphiteConfiguration {

        @Autowired
        private MetricRegistry metricRegistry;

        @PostConstruct
        public void startGraphiteReporter() throws UnknownHostException {
            String hostname = InetAddress.getLocalHost().getHostName();

            Graphite graphite = new Graphite("192.168.99.100", 2003);
            GraphiteReporter reporter = GraphiteReporter
                    .forRegistry(metricRegistry)
                    .prefixedWith("services.lunchbox." + hostname)
                    .build(graphite);
            reporter.start(10, TimeUnit.SECONDS);
        }
    }
}
