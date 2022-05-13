package pl.polsl.zbdihd.wss.domain.currency;

import pl.polsl.zbdihd.wss.domain.Versionable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CurrencyRate(CurrencyCode code,
                           BigDecimal rate,
                           LocalDateTime versionDateTime) implements Versionable {
}
