package pl.polsl.zbdihd.wss.config;

import lombok.ToString;
import lombok.Value;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;
import pl.polsl.zbdihd.wss.algorithm.JobOrderingAlgorithmType;
import pl.polsl.zbdihd.wss.domain.TableType;
import pl.polsl.zbdihd.wss.scheduling.distribution.NormalDurationDistribution;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@ConfigurationProperties(prefix = "wss")
@ConstructorBinding
@Validated
@ToString
@Value
public class WssConfig {

    @NotNull
    Duration simulationTime;

    @NotNull
    RandomGenerator randomGenerator;

    @NotNull
    @Positive
    Integer trackCount;

    @NotNull
    JobOrderingAlgorithmType algorithm;

    @NotEmpty
    @Size(min = 3)
    Map<TableType, JobConfig> jobs;

    public WssConfig(final Duration simulationTime,
                     final Integer seed,
                     final Integer trackCount,
                     final JobOrderingAlgorithmType algorithm,
                     final Map<TableType, JobConfig> jobs) {
        this.simulationTime = simulationTime;
        this.randomGenerator = Optional.ofNullable(seed)
                .map(JDKRandomGenerator::new)
                .orElseGet(JDKRandomGenerator::new);
        this.trackCount = trackCount;
        this.algorithm = algorithm;
        this.jobs = jobs;
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
        return () -> randomGenerator.nextInt(trackCount);
    }

    public record JobConfig(@NotNull Integer priority,
                            @NotNull WssConfig.DistributionConfig generationDistribution,
                            @NotNull WssConfig.DistributionConfig executionTimeDistribution,
                            @NotNull WssConfig.DistributionConfig deadlineDistribution) {
    }

    public record DistributionConfig(@NotNull Duration mean,
                                     @NotNull Duration stdDev) {
    }

}
