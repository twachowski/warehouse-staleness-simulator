package pl.polsl.zbdihd.wss.scheduling.event.simulation;

import lombok.Getter;
import pl.polsl.zbdihd.wss.scheduling.event.WarehouseEvent;

import java.time.Duration;

@Getter
public final class SimulationTimeReachedEvent extends WarehouseEvent {

    private final Duration simulationTime;

    public SimulationTimeReachedEvent(final Duration simulationTime) {
        super(simulationTime);
        this.simulationTime = simulationTime;
    }

}
