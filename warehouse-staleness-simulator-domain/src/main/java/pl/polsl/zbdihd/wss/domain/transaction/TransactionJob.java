package pl.polsl.zbdihd.wss.domain.transaction;

import pl.polsl.zbdihd.wss.domain.Job;
import pl.polsl.zbdihd.wss.domain.TableType;

import java.time.Duration;
import java.util.Set;

public record TransactionJob(Duration executionTime,
                             Set<Transaction> records,
                             Duration deadline) implements Job<Transaction> {

    @Override
    public TableType tableType() {
        return TableType.TRANSACTION;
    }

}
