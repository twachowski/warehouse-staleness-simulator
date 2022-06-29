package pl.polsl.zbdihd.wss.scheduling.event;

import lombok.Getter;
import lombok.ToString;
import pl.polsl.zbdihd.wss.domain.Job;
import pl.polsl.zbdihd.wss.domain.Versionable;

@Getter
@ToString
public abstract class NewJobEvent<T extends Versionable> extends WarehouseTrackEvent {

    private final Job<T> job;

    NewJobEvent(final Job<T> job, final int trackId) {
        super(trackId);
        this.job = job;
    }

}
