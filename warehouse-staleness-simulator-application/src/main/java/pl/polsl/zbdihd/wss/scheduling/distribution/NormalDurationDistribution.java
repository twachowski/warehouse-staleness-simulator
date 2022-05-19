package pl.polsl.zbdihd.wss.scheduling.distribution;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.RandomGenerator;

import java.time.Duration;

public class NormalDurationDistribution extends NormalDistribution {

    public NormalDurationDistribution(final RandomGenerator randomGenerator,
                                      final Duration mean,
                                      final Duration stdDev) {
        super(randomGenerator, mean.toMillis(), stdDev.toMillis());
    }

    public Duration generateSample() {
        return Duration.ofMillis((long) sample());
    }

}
