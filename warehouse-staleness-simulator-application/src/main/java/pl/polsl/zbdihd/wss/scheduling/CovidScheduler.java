package pl.polsl.zbdihd.wss.scheduling;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pl.polsl.zbdihd.wss.config.WssConfig;
import pl.polsl.zbdihd.wss.domain.TableType;
import pl.polsl.zbdihd.wss.domain.covid.CovidJob;
import pl.polsl.zbdihd.wss.domain.covid.CovidReport;
import pl.polsl.zbdihd.wss.scheduling.event.CovidReportEvent;

import java.util.Set;

@Component
public class CovidScheduler extends Scheduler<CovidReport, CovidJob, CovidReportEvent> {

    public CovidScheduler(final WssConfig config, final ApplicationEventPublisher eventPublisher) {
        super(config,
              TableType.COVID,
              CovidJob::new,
              CovidReportEvent::new,
              eventPublisher);
    }

    @Override
    protected Set<CovidReport> generateRecords() {
        return Set.of();    //TODO
    }

}
