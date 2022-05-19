package pl.polsl.zbdihd.wss.scheduling;

import io.vavr.Function2;
import io.vavr.Function3;
import org.apache.commons.math3.random.RandomGenerator;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import pl.polsl.zbdihd.wss.config.WssConfig;
import pl.polsl.zbdihd.wss.domain.Job;
import pl.polsl.zbdihd.wss.domain.TableType;
import pl.polsl.zbdihd.wss.domain.Versionable;
import pl.polsl.zbdihd.wss.scheduling.distribution.NormalDurationDistribution;
import pl.polsl.zbdihd.wss.scheduling.event.WarehouseEvent;

import java.time.Duration;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

abstract class Scheduler<TRecord extends Versionable, TJob extends Job<TRecord>, TEvent extends WarehouseEvent<TRecord>> implements ApplicationListener<ApplicationReadyEvent> {

    private final NormalDurationDistribution generationDistribution;
    private final NormalDurationDistribution executionTimeDistribution;
    private final NormalDurationDistribution deadlineDistribution;
    private final int recordsLimit;
    private final Supplier<Integer> trackIdSupplier;
    private final Function3<Duration, Set<TRecord>, Duration, TJob> jobCreator;
    private final Function2<TJob, Integer, TEvent> eventCreator;
    private final ApplicationEventPublisher eventPublisher;
    protected final RandomGenerator randomGenerator;

    protected Scheduler(final WssConfig config,
                        final TableType tableType,
                        final Function3<Duration, Set<TRecord>, Duration, TJob> jobCreator,
                        final Function2<TJob, Integer, TEvent> eventCreator,
                        final ApplicationEventPublisher eventPublisher) {
        final WssConfig.JobConfig jobConfig = config.getJobConfig(tableType);
        this.generationDistribution = config.createDistribution(jobConfig.generationDistribution());
        this.executionTimeDistribution = config.createDistribution(jobConfig.executionTimeDistribution());
        this.deadlineDistribution = config.createDistribution(jobConfig.deadlineDistribution());
        this.recordsLimit = jobConfig.recordsLimit();
        this.trackIdSupplier = config.getTrackIdSupplier();
        this.jobCreator = jobCreator;
        this.eventCreator = eventCreator;
        this.eventPublisher = eventPublisher;
        this.randomGenerator = config.getRandomGenerator();
    }

    protected abstract TRecord generateRecord();

    private Set<TRecord> generateRecords() {
        return IntStream.rangeClosed(1, getNumberOfRecordsToGenerate())
                        .mapToObj(i -> generateRecord())
                        .collect(Collectors.toSet());
    }

    private int getNumberOfRecordsToGenerate() {
        return randomGenerator.nextInt(recordsLimit) + 1;
    }

    private TEvent generateEvent() {
        final Set<TRecord> records = generateRecords();
        final Duration executionTime = executionTimeDistribution.generateSample();
        final Duration deadline = deadlineDistribution.generateSample();
        final TJob job = jobCreator.apply(executionTime,
                                          records,
                                          deadline.plus(executionTime));
        return eventCreator.apply(job, trackIdSupplier.get());
    }

    private void scheduleNext(final TEvent event) {
        eventPublisher.publishEvent(event);
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        scheduleNext(generateEvent());
    }

}
