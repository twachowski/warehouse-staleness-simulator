package pl.polsl.zbdihd.wss.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

public interface Job<T> {

    TableType tableType();

    Duration executionTime();

    Set<T> records();

    LocalDateTime deadline();

}
