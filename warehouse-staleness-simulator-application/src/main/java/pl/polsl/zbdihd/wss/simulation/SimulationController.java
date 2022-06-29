package pl.polsl.zbdihd.wss.simulation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import pl.polsl.zbdihd.wss.config.WssConfig;
import pl.polsl.zbdihd.wss.scheduling.event.simulation.SimulationTimeReachedEvent;
import pl.polsl.zbdihd.wss.scheduling.event.simulation.TrackProcessingFinishedEvent;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class SimulationController {

    private final ConfigurableApplicationContext applicationContext;
    private final Set<Integer> finishedTracks = new HashSet<>(WssConfig.TRACK_COUNT);

    public SimulationController(final WssConfig config,
                                final ApplicationEventPublisher eventPublisher,
                                final TaskScheduler taskScheduler,
                                final ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        log.info("Running simulation with config: " + config);
        taskScheduler.schedule(() -> eventPublisher.publishEvent(new SimulationTimeReachedEvent(config.getSimulationTime())),
                               new Date(System.currentTimeMillis() + config.getSimulationTime().toMillis()));
    }

    @EventListener(TrackProcessingFinishedEvent.class)
    public void onTrackProcessingFinishedEvent(final TrackProcessingFinishedEvent event) {
        log.info("Track {} has finished processing jobs", event.getTrackId());
        finishedTracks.add(event.getTrackId());
        if (finishedTracks.size() == WssConfig.TRACK_COUNT) {
            log.info("All tracks have finished processing jobs, shutting down the application...");
            applicationContext.close();
        }
    }

}
