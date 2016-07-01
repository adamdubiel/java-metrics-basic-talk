package pl.confitura.lunchbox.domain;

public class LunchPlace {

    private final String name;

    private int score;

    public LunchPlace(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public LunchPlace(String name) {
        this(name, 0);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void upvote(int amount) {
        this.score += amount;
    }

    public void downvote(int amount) {
        this.score -= amount;
    }
}
