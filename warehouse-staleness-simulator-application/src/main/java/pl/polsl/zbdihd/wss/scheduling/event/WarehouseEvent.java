package pl.polsl.zbdihd.wss.scheduling.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import pl.polsl.zbdihd.wss.domain.Job;
import pl.polsl.zbdihd.wss.domain.Versionable;

@Getter
public abstract class WarehouseEvent<T extends Versionable> extends ApplicationEvent {

    private final Job<T> job;
    private final int trackId;

    WarehouseEvent(final Job<T> job, final int trackId) {
        super(job);
        this.job = job;
        this.trackId = trackId;
    }

}
