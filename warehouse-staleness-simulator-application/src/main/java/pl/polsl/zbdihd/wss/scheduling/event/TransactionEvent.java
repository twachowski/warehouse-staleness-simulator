package pl.polsl.zbdihd.wss.scheduling.event;

import pl.polsl.zbdihd.wss.domain.transaction.Transaction;
import pl.polsl.zbdihd.wss.domain.transaction.TransactionJob;

public class TransactionEvent extends WarehouseEvent<Transaction> {

    public TransactionEvent(final TransactionJob job, final int trackId) {
        super(job, trackId);
    }

}
