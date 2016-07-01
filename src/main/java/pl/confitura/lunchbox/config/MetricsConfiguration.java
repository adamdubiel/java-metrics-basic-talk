package pl.confitura.lunchbox.config;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableMetrics
public class MetricsConfiguration {

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
