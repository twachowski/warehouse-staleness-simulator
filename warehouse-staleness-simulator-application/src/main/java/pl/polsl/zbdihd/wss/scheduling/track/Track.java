package pl.polsl.zbdihd.wss.scheduling.track;

import lombok.Value;
import org.springframework.context.ApplicationListener;
import pl.polsl.zbdihd.wss.domain.Job;
import pl.polsl.zbdihd.wss.domain.Versionable;
import pl.polsl.zbdihd.wss.scheduling.event.WarehouseEvent;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

@Value
public class Track implements ApplicationListener<WarehouseEvent> {

    int id;
    Queue<Job<? extends Versionable>> jobs;

    public Track(final int id) {
        this.id = id;
        this.jobs = new PriorityQueue<>();
    }

    @Override
    public void onApplicationEvent(final WarehouseEvent event) {
        if (event.getTrackId() == id) {
            jobs.add(event.getJob());
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Track that) {
            return this.id == that.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
