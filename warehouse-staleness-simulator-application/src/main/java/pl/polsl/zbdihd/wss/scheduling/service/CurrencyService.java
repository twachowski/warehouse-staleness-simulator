package pl.polsl.zbdihd.wss.scheduling.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.polsl.zbdihd.wss.domain.Job;
import pl.polsl.zbdihd.wss.domain.TableType;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyCode;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyJob;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyRate;
import pl.polsl.zbdihd.wss.persistence.entity.CurrencyEntity;
import pl.polsl.zbdihd.wss.persistence.repository.CurrencyEntityRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CurrencyService extends JobExecutor<CurrencyJob> {

    private final CurrencyEntityRepository currencyRepository;

    public CurrencyService(final ApplicationEventPublisher eventPublisher,
                           final CurrencyEntityRepository currencyRepository) {
        super(TableType.CURRENCY, eventPublisher);
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void execute(final Job<?> job, final int trackId) {
        if (job instanceof CurrencyJob currencyJob) {
            execute(currencyJob, trackId);
        } else {
            throw new IllegalArgumentException("Unsupported job: " + job);
        }
    }

    private void execute(final CurrencyJob job, final int trackId) {
        final LocalDateTime executionStartTime = LocalDateTime.now();
        final Map<CurrencyCode, CurrencyRate> currencyRates = job.records()
                                                                 .stream()
                                                                 .collect(Collectors.toMap(CurrencyRate::code, Function.identity()));
        final Set<CurrencyEntity> currencies = currencyRepository.findAllById(currencyRates.keySet())
                                                                 .stream()
                                                                 .map(currency -> currency.withRateChange(currencyRates.get(currency.getCode())))
                                                                 .collect(Collectors.toSet());
        currencyRepository.saveAll(currencies);
        simulateExecution(job, executionStartTime, trackId);
    }

}
