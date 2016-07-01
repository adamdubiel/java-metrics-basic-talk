package pl.confitura.lunchbox.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LunchPlacesService {

    private final LunchPlacesRepository repository;

    @Autowired
    public LunchPlacesService(LunchPlacesRepository repository) {
        this.repository = repository;
    }

    public List<LunchPlace> list() {
        return repository.list();
    }

    public void upvote(String lunchPlace) {
        repository.upvote(lunchPlace, 1);
    }

    public void downvote(String lunchPlace) {
        repository.downvote(lunchPlace, 1);
    }
}
