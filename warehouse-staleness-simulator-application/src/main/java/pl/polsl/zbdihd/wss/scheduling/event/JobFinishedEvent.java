package pl.polsl.zbdihd.wss.scheduling.event;

public class JobFinishedEvent extends WarehouseTrackEvent {

    public JobFinishedEvent(final int trackId) {
        super(trackId);
    }

}
