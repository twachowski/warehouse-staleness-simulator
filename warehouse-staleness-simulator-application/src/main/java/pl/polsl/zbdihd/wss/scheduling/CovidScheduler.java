package pl.polsl.zbdihd.wss.scheduling;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pl.polsl.zbdihd.wss.config.WssConfig;
import pl.polsl.zbdihd.wss.domain.TableType;
import pl.polsl.zbdihd.wss.domain.covid.CovidJob;
import pl.polsl.zbdihd.wss.domain.covid.CovidReport;
import pl.polsl.zbdihd.wss.domain.covid.Voivodeship;
import pl.polsl.zbdihd.wss.scheduling.event.CovidReportEvent;

import java.time.LocalDateTime;

@Component
public class CovidScheduler extends Scheduler<CovidReport, CovidJob, CovidReportEvent> {

    private static final int INFECTIONS_LIMIT = 1000;
    private static final int DEATHS_LIMIT = 15;
    private static final int VACCINATIONS_LIMIT = 50;

    public CovidScheduler(final WssConfig config, final ApplicationEventPublisher eventPublisher) {
        super(config,
              TableType.COVID,
              CovidJob::new,
              CovidReportEvent::new,
              eventPublisher);
    }

    @Override
    protected CovidReport generateRecord() {
        final Voivodeship voivodeship = Voivodeship.get(randomGenerator.nextInt());
        final int infections = randomGenerator.nextInt(INFECTIONS_LIMIT);
        final int deaths = randomGenerator.nextInt(DEATHS_LIMIT);
        final int vaccinations = randomGenerator.nextInt(VACCINATIONS_LIMIT);
        return new CovidReport(voivodeship,
                               infections,
                               deaths,
                               vaccinations,
                               LocalDateTime.now());
    }

}
