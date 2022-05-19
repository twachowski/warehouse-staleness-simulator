package pl.polsl.zbdihd.wss.scheduling;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pl.polsl.zbdihd.wss.config.WssConfig;
import pl.polsl.zbdihd.wss.domain.TableType;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyJob;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyRate;
import pl.polsl.zbdihd.wss.scheduling.event.CurrencyRateEvent;

import java.util.Set;

@Component
public class CurrencyScheduler extends Scheduler<CurrencyRate, CurrencyJob, CurrencyRateEvent> {

    public CurrencyScheduler(final WssConfig config, final ApplicationEventPublisher eventPublisher) {
        super(config,
              TableType.CURRENCY,
              CurrencyJob::new,
              CurrencyRateEvent::new,
              eventPublisher);
    }

    @Override
    protected Set<CurrencyRate> generateRecords() {
        return Set.of();    //TODO
    }

}
