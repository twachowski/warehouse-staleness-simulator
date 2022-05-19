package pl.polsl.zbdihd.wss.scheduling.event;

import pl.polsl.zbdihd.wss.domain.currency.CurrencyJob;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyRate;

public class CurrencyRateEvent extends WarehouseEvent<CurrencyRate> {

    public CurrencyRateEvent(final CurrencyJob job, final int trackId) {
        super(job, trackId);
    }

}
