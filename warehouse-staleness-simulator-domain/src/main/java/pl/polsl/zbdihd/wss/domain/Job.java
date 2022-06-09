package pl.polsl.zbdihd.wss.domain;

import java.time.Duration;
import java.util.Set;

public interface Job<T> {

    TableType tableType();

    Duration executionTime();

    Set<T> records();

    Duration deadline();

    default void execute() throws InterruptedException {
        Thread.sleep(executionTime().toMillis());
    }

}
