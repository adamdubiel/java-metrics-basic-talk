package pl.confitura.lunchbox.infrastructure;

import java.util.concurrent.ThreadLocalRandom;

public final class DelayMaker {

    public static void delay(int min, int max) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(min, max));
        } catch (InterruptedException e) {
            // not very elegant, but how often do you implement
            // repository that adds artificial delay? ;)
        }
    }

}
