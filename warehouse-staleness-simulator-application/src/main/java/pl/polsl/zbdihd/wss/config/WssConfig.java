package pl.polsl.zbdihd.wss.config;

import lombok.ToString;
import lombok.Value;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.validation.annotation.Validated;
import pl.polsl.zbdihd.wss.algorithm.*;
import pl.polsl.zbdihd.wss.domain.TableType;
import pl.polsl.zbdihd.wss.scheduling.distribution.NormalDurationDistribution;
import pl.polsl.zbdihd.wss.scheduling.service.JobExecutor;
import pl.polsl.zbdihd.wss.scheduling.track.Track;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

@ConfigurationProperties(prefix = "wss")
@ConstructorBinding
@Validated
@ToString
@Value
public class WssConfig extends AsyncConfigurerSupport {

    @NotNull
    Duration simulationTime;

    @NotNull
    RandomGenerator randomGenerator;

    @NotNull
    JobOrderingAlgorithm jobOrderingAlgorithm;

    @NotEmpty
    @Size(min = 3)
    Map<TableType, JobConfig> jobs;

    public WssConfig(final Duration simulationTime,
                     final Integer seed,
                     final JobOrderingAlgorithmType algorithm,
                     final Map<TableType, JobConfig> jobs) {
        this.simulationTime = simulationTime;
        this.randomGenerator = Optional.ofNullable(seed)
                                       .map(JDKRandomGenerator::new)
                                       .orElseGet(JDKRandomGenerator::new);
        this.jobOrderingAlgorithm = createAlgorithm(algorithm);
        this.jobs = jobs;
    }

    @Bean
    Track track0(final Set<JobExecutor<?>> jobExecutors) {
        return new Track(0, jobOrderingAlgorithm, jobExecutors);
    }

    @Bean
    Track track1(final Set<JobExecutor<?>> jobExecutors) {
        return new Track(1, jobOrderingAlgorithm, jobExecutors);
    }

    @Bean
    Track track2(final Set<JobExecutor<?>> jobExecutors) {
        return new Track(2, jobOrderingAlgorithm, jobExecutors);
    }

    @Bean
    Track track3(final Set<JobExecutor<?>> jobExecutors) {
        return new Track(3, jobOrderingAlgorithm, jobExecutors);
    }

    @Bean
    TaskScheduler taskScheduler() {
        final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }

    @Override
    public Executor getAsyncExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.initialize();
        return executor;
    }

    public JobConfig getJobConfig(final TableType tableType) {
        return jobs.get(tableType);
    }

    public NormalDurationDistribution createDistribution(final DistributionConfig distributionConfig) {
        return new NormalDurationDistribution(this.randomGenerator,
                                              distributionConfig.mean,
                                              distributionConfig.stdDev);
    }

    public Supplier<Integer> getTrackIdSupplier() {
        return () -> randomGenerator.nextInt(4);
    }

    public record JobConfig(@NotNull Integer priority,
                            @NotNull @Positive Integer recordsLimit,
                            @NotNull Duration versionAgeLimit,
                            @NotNull WssConfig.DistributionConfig generationDistribution,
                            @NotNull WssConfig.DistributionConfig executionTimeDistribution,
                            @NotNull WssConfig.DistributionConfig deadlineDistribution) {
    }

    public record DistributionConfig(@NotNull Duration mean,
                                     @NotNull Duration stdDev) {
    }

    private static JobOrderingAlgorithm createAlgorithm(final JobOrderingAlgorithmType algorithmType) {
        return switch (algorithmType) {
            case EDF -> new EarliestDeadlineFirst();
            case MAXB -> new MaxBenefit();
            case EDFP -> new PrioritizedEarliestDeadlineFirst();
        };
    }

}
