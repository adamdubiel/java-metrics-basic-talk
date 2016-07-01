package pl.confitura.lunchbox.domain;

import java.util.List;

public interface LunchPlacesRepository {

    List<LunchPlace> list();

    void add(LunchPlace lunchPlace);

    void upvote(String name, int amount);

    void downvote(String name, int amount);

}
