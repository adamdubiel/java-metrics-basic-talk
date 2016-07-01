package pl.confitura.lunchbox.infrastructure.leader;

import org.springframework.stereotype.Component;

@Component
public class LeaderLock {

    public boolean isLeader() {
        return true;
    }

}
