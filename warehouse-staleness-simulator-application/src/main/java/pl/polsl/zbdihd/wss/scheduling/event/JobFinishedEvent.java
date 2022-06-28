package pl.polsl.zbdihd.wss.scheduling.event;

public class JobFinishedEvent extends WarehouseEvent {

    public JobFinishedEvent(final int trackId) {
        super(trackId);
    }

}
