package pl.confitura.lunchbox.infrastructure.refresh;

import org.springframework.stereotype.Component;
import pl.confitura.lunchbox.domain.LunchPlace;
import pl.confitura.lunchbox.infrastructure.DelayMaker;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExternalServiceClient {

    private static final List<LunchPlace> PLACES = Arrays.asList(
        new LunchPlace("TukTuk"), new LunchPlace("Fraza")
    );

    @SuppressWarnings("unchecked")
    public List<LunchPlace> fetchPlaces() {
        DelayMaker.delay(400, 2500);
        return PLACES.stream()
                .map(l -> new LunchPlace(l.getName()))
                .collect(Collectors.toList());
    }
}
