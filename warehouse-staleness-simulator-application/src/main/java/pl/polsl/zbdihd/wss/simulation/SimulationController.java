package pl.polsl.zbdihd.wss.simulation;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.polsl.zbdihd.wss.config.WssConfig;

import java.time.Duration;

@Component
@Slf4j
public class SimulationController implements ApplicationListener<ApplicationReadyEvent> {

    private final StopWatch watch = StopWatch.create();

    public SimulationController(final WssConfig config, final ApplicationEventPublisher eventPublisher) {
        log.info("Running simulation with config: " + config);
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        watch.start();
    }

    public Duration getSimulationTime() {
        return Duration.ofNanos(watch.getNanoTime());
    }

    @EventListener(SimulationEndedEvent.class)
    public void onSimulationEnd(final SimulationEndedEvent event) {
        log.info("{} seconds have passed, terminating the simulation", event.getSimulationTime().getSeconds());
    }

    @Getter
    private static final class SimulationEndedEvent extends ApplicationEvent {

        private final Duration simulationTime;

        public SimulationEndedEvent(final Duration simulationTime) {
            super(simulationTime);
            this.simulationTime = simulationTime;
        }

    }

}
