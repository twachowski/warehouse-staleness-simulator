package pl.polsl.zbdihd.wss.scheduling.event.simulation;

import lombok.Getter;
import pl.polsl.zbdihd.wss.scheduling.event.WarehouseTrackEvent;

@Getter
public class TrackProcessingFinishedEvent extends WarehouseTrackEvent {

    public TrackProcessingFinishedEvent(final int trackId) {
        super(trackId);
    }

}
