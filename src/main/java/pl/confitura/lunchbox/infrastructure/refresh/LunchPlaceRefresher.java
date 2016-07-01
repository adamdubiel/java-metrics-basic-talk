package pl.confitura.lunchbox.infrastructure.refresh;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.confitura.lunchbox.domain.LunchPlacesRepository;
import pl.confitura.lunchbox.infrastructure.leader.LeaderLock;

@Component
public class LunchPlaceRefresher {

    private static final Logger logger = LoggerFactory.getLogger(LunchPlaceRefresher.class);

    private final ExternalServiceClient externalServiceClient;

    private final LunchPlacesRepository repository;

    private final LeaderLock leaderLock;

    private long lastRunDuration;

    @Autowired
    public LunchPlaceRefresher(ExternalServiceClient externalServiceClient,
                               LunchPlacesRepository repository,
                               LeaderLock leaderLock,
                               MetricRegistry metricRegistry) {
        this.externalServiceClient = externalServiceClient;
        this.repository = repository;
        this.leaderLock = leaderLock;
        metricRegistry.register(
                "background.refreshPlaces",
                (Gauge<Long>) () -> lastRunDuration
        );
    }

    @Scheduled(fixedDelayString = "${refresher.delay:60000}")
    public void refreshLunchPlaces() {
        long startTime = System.currentTimeMillis();
        if (leaderLock.isLeader()) {
            logger.info("Start refreshing lunch places list");

            externalServiceClient.fetchPlaces().stream().forEach(repository::add);

            logger.info("Done refreshing lunch places list");
        }
        lastRunDuration = System.currentTimeMillis() - startTime;
    }
}
