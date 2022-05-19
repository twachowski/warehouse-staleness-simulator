package pl.polsl.zbdihd.wss.scheduling;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pl.polsl.zbdihd.wss.config.WssConfig;
import pl.polsl.zbdihd.wss.domain.TableType;
import pl.polsl.zbdihd.wss.domain.transaction.Transaction;
import pl.polsl.zbdihd.wss.domain.transaction.TransactionJob;
import pl.polsl.zbdihd.wss.scheduling.event.TransactionEvent;

import java.util.Set;

@Component
public class TransactionScheduler extends Scheduler<Transaction, TransactionJob, TransactionEvent> {

    public TransactionScheduler(final WssConfig config, final ApplicationEventPublisher eventPublisher) {
        super(config,
              TableType.TRANSACTION,
              TransactionJob::new,
              TransactionEvent::new,
              eventPublisher);
    }

    @Override
    protected Set<Transaction> generateRecords() {
        return Set.of();    //TODO
    }

}
