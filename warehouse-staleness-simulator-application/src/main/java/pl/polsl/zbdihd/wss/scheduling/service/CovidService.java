package pl.polsl.zbdihd.wss.scheduling.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.polsl.zbdihd.wss.domain.Job;
import pl.polsl.zbdihd.wss.domain.TableType;
import pl.polsl.zbdihd.wss.domain.covid.CovidJob;
import pl.polsl.zbdihd.wss.persistence.entity.CovidReportEntity;
import pl.polsl.zbdihd.wss.persistence.repository.CovidReportEntityRepository;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CovidService extends JobExecutor<CovidJob> {

    private final CovidReportEntityRepository covidReportRepository;

    public CovidService(final ApplicationEventPublisher eventPublisher,
                        final CovidReportEntityRepository covidReportRepository) {
        super(TableType.COVID, eventPublisher);
        this.covidReportRepository = covidReportRepository;
    }

    @Override
    public void execute(final Job<?> job, final int trackId) {
        if (job instanceof CovidJob covidJob) {
            execute(covidJob, trackId);
        } else {
            throw new IllegalArgumentException("Unsupported job: " + job);
        }
    }

    private void execute(final CovidJob job, final int trackId) {
        final LocalDateTime executionStartTime = LocalDateTime.now();
        final Set<CovidReportEntity> covidReports = job.records()
                                                       .stream()
                                                       .map(CovidReportEntity::valueOf)
                                                       .collect(Collectors.toSet());
        covidReportRepository.saveAll(covidReports);
        simulateExecution(job, executionStartTime, trackId);
    }

}
