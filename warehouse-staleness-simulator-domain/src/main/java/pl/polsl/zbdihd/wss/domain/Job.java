package pl.polsl.zbdihd.wss.domain;

import java.time.Duration;
import java.util.Set;

public interface Job<T extends Versionable> {

    TableType tableType();

    Duration executionTime();

    Set<T> records();

    Duration deadline();

}
