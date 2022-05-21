package pl.polsl.zbdihd.wss.scheduling;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pl.polsl.zbdihd.wss.config.WssConfig;
import pl.polsl.zbdihd.wss.domain.TableType;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyCode;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyJob;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyRate;
import pl.polsl.zbdihd.wss.scheduling.event.CurrencyRateEvent;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Set;

@Component
public class CurrencyScheduler extends Scheduler<CurrencyRate, CurrencyJob, CurrencyRateEvent> {

    /**
     * Base currency (EUR) and other currencies with missing exchange rates.
     */
    private static final Set<CurrencyCode> FORBIDDEN_CURRENCIES = EnumSet.of(CurrencyCode.BOV,
                                                                             CurrencyCode.CHE,
                                                                             CurrencyCode.CHW,
                                                                             CurrencyCode.COU,
                                                                             CurrencyCode.EUR,
                                                                             CurrencyCode.USN,
                                                                             CurrencyCode.UYI,
                                                                             CurrencyCode.UYW,
                                                                             CurrencyCode.XBA,
                                                                             CurrencyCode.XBB,
                                                                             CurrencyCode.XBC,
                                                                             CurrencyCode.XBD,
                                                                             CurrencyCode.XSU,
                                                                             CurrencyCode.XTS,
                                                                             CurrencyCode.XUA,
                                                                             CurrencyCode.XXX);

    private static final float RATE_CHANGE_LIMIT = 0.1f;

    public CurrencyScheduler(final WssConfig config, final ApplicationEventPublisher eventPublisher) {
        super(config,
              TableType.CURRENCY,
              CurrencyJob::new,
              CurrencyRateEvent::new,
              eventPublisher);
    }

    @Override
    protected CurrencyRate generateRecord() {
        final CurrencyCode currencyCode = getCurrencyCode();
        final float rateChange = randomGenerator.nextFloat() * RATE_CHANGE_LIMIT;
        return new CurrencyRate(currencyCode,
                                BigDecimal.valueOf(rateChange),
                                generateVersionDateTime());
    }

    private CurrencyCode getCurrencyCode() {
        while (true) {
            final CurrencyCode currencyCode = CurrencyCode.get(randomGenerator.nextInt());
            if (!FORBIDDEN_CURRENCIES.contains(currencyCode)) {
                return currencyCode;
            }
        }
    }

}
