package pl.polsl.zbdihd.wss.domain.covid;

import pl.polsl.zbdihd.wss.domain.Job;
import pl.polsl.zbdihd.wss.domain.TableType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

public record CovidJob(Duration executionTime,
                       Set<CovidReport> records,
                       LocalDateTime deadline) implements Job<CovidReport> {

    @Override
    public TableType tableType() {
        return TableType.COVID;
    }

}
