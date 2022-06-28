package pl.polsl.zbdihd.wss.scheduling;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import pl.polsl.zbdihd.wss.config.WssConfig;
import pl.polsl.zbdihd.wss.domain.TableType;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyCode;
import pl.polsl.zbdihd.wss.domain.transaction.Transaction;
import pl.polsl.zbdihd.wss.domain.transaction.TransactionJob;
import pl.polsl.zbdihd.wss.scheduling.event.TransactionJobEvent;

import java.math.BigDecimal;

@Component
public class TransactionScheduler extends Scheduler<Transaction, TransactionJob, TransactionJobEvent> {

    private static final long TRANSACTION_AMOUNT_LIMIT = 5000L;

    public TransactionScheduler(final WssConfig config,
                                final ApplicationEventPublisher eventPublisher,
                                final TaskScheduler taskScheduler) {
        super(config,
              TableType.TRANSACTION,
              TransactionJob::new,
              TransactionJobEvent::new,
              eventPublisher,
              taskScheduler);
    }

    @Override
    protected Transaction generateRecord() {
        final CurrencyCode currencyCode = CurrencyCode.get(randomGenerator.nextInt(CurrencyCode.size()));
        final long amount = randomGenerator.nextLong() % TRANSACTION_AMOUNT_LIMIT;
        return new Transaction(currencyCode,
                               BigDecimal.valueOf(amount),
                               generateVersionDateTime());
    }

}
