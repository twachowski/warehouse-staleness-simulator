package pl.polsl.zbdihd.wss.scheduling.event;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class WarehouseTrackEvent extends WarehouseEvent {

    private final int trackId;

    protected WarehouseTrackEvent(final int trackId) {
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
