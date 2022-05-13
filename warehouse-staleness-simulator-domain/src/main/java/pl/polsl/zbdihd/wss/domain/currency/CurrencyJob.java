package pl.polsl.zbdihd.wss.domain.currency;

import pl.polsl.zbdihd.wss.domain.Job;
import pl.polsl.zbdihd.wss.domain.TableType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

public record CurrencyJob(Duration executionTime,
                          Set<CurrencyRate> records,
                          LocalDateTime deadline) implements Job<CurrencyRate> {

    @Override
    public TableType tableType() {
        return TableType.CURRENCY;
    }

}
