package pl.confitura.lunchbox.domain;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import org.jctools.queues.MpscArrayQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeteredQueueFactory {

    private final MetricRegistry registry;

    @Autowired
    public MeteredQueueFactory(MetricRegistry registry) {
        this.registry = registry;
    }

    public <T> MpscArrayQueue<T> create(String name, int capacity) {
        MpscArrayQueue<T> queue = new MpscArrayQueue<T>(capacity);

        registry.register("queue." + name + ".utilization", (Gauge<Double>) () -> (double) queue.size() / (double) queue.capacity());

        return queue;
    }

}
