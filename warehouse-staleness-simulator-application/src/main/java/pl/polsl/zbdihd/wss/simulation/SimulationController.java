package pl.polsl.zbdihd.wss.simulation;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.polsl.zbdihd.wss.config.WssConfig;

import java.time.Duration;

@Component
@Slf4j
public class SimulationController implements ApplicationListener<ApplicationReadyEvent> {

    private final StopWatch watch = StopWatch.create();

    public SimulationController(final WssConfig config) {
        log.info("Running simulation with config: " + config);
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        watch.start();
    }

    public Duration getSimulationTime() {
        return Duration.ofNanos(watch.getNanoTime());
    }

}
