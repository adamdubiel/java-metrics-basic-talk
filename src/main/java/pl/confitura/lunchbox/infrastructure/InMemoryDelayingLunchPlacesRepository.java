package pl.confitura.lunchbox.infrastructure;

import org.springframework.stereotype.Component;
import pl.confitura.lunchbox.domain.LunchPlace;
import pl.confitura.lunchbox.domain.LunchPlacesRepository;
import pl.confitura.lunchbox.domain.UnknownLunchPlaceException;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Component
public class InMemoryDelayingLunchPlacesRepository implements LunchPlacesRepository {

    private final ConcurrentMap<String, LunchPlace> repository = new ConcurrentHashMap<>();

    @Override
    public List<LunchPlace> list() {
        DelayMaker.delay(100, 800);
        return repository.values()
                .stream()
                .sorted(Comparator.comparing(l -> l.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public void add(LunchPlace lunchPlace) {
        DelayMaker.delay(50, 150);
        repository.computeIfAbsent(lunchPlace.getName(), (k) -> lunchPlace);
    }

    @Override
    public void upvote(String name, int amount) {
        registerVote(name, (k, v) -> {
            v.upvote(amount);
            return v;
        });
    }

    @Override
    public void downvote(String name, int amount) {
        registerVote(name, (k, v) -> {
            v.downvote(amount);
            return v;
        });
    }

    private void registerVote(String name, BiFunction<String, LunchPlace, LunchPlace> function) {
        DelayMaker.delay(2, 5);
        LunchPlace place = repository.computeIfPresent(name, function);
        if (place == null) {
            throw new UnknownLunchPlaceException();
        }
    }
}
