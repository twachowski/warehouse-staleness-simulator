package pl.polsl.zbdihd.wss.domain.transaction;

import pl.polsl.zbdihd.wss.domain.Versionable;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(CurrencyCode currencyCode,
                          BigDecimal amount,
                          LocalDateTime creationDateTime,
                          LocalDateTime versionDateTime) implements Versionable {
}
