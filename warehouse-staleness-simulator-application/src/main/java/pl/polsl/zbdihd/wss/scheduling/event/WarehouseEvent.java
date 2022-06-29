package pl.polsl.zbdihd.wss.scheduling.event;

import org.springframework.context.ApplicationEvent;
import pl.polsl.zbdihd.wss.scheduling.event.simulation.SimulationTimeReachedEvent;

public abstract class WarehouseEvent extends ApplicationEvent {

    public WarehouseEvent(final Object source) {
        super(source);
    }

    public boolean isTrackEvent() {
        return this instanceof WarehouseTrackEvent;
    }

    public WarehouseTrackEvent asTrackEvent() {
        return (WarehouseTrackEvent) this;
    }

    public boolean isSimulationTimeReached() {
        return this instanceof SimulationTimeReachedEvent;
    }

}
