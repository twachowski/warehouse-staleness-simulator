package pl.polsl.zbdihd.wss.scheduling.event;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public abstract class WarehouseEvent extends ApplicationEvent {

    private final int trackId;

    protected WarehouseEvent(final int trackId) {
        super(trackId);
        this.trackId = trackId;
    }

    public boolean hasNewJob() {
        return this instanceof NewJobEvent;
    }

    public NewJobEvent<?> asNewJobEvent() {
        return (NewJobEvent<?>) this;
    }

    public boolean isJobFinished() {
        return this instanceof JobFinishedEvent;
    }

}
