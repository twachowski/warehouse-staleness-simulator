package pl.polsl.zbdihd.wss.scheduling.service;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationEventPublisher;
import pl.polsl.zbdihd.wss.domain.Job;
import pl.polsl.zbdihd.wss.domain.TableType;
import pl.polsl.zbdihd.wss.scheduling.event.JobFinishedEvent;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class JobExecutor<T extends Job<?>> {

    private final TableType tableType;
    private final ApplicationEventPublisher eventPublisher;

    protected JobExecutor(final TableType tableType, final ApplicationEventPublisher eventPublisher) {
        this.tableType = tableType;
        this.eventPublisher = eventPublisher;
    }

    public boolean supports(final Job<?> job) {
        return job.tableType() == tableType;
    }

    public abstract void execute(Job<?> job, final int trackId);

    @SneakyThrows
    protected void simulateExecution(final T job,
                                     final LocalDateTime executionStartTime,
                                     final int trackId) {
        final Duration realExecutionTime = Duration.between(executionStartTime, LocalDateTime.now());
        final Duration sleepTime = job.executionTime().minus(realExecutionTime);
        if (!sleepTime.isNegative()) {
            Thread.sleep(sleepTime.toMillis());
        }
        eventPublisher.publishEvent(new JobFinishedEvent(trackId));
    }

}
