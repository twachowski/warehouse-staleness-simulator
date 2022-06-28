package pl.polsl.zbdihd.wss.scheduling.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.polsl.zbdihd.wss.domain.Job;
import pl.polsl.zbdihd.wss.domain.TableType;
import pl.polsl.zbdihd.wss.domain.transaction.TransactionJob;
import pl.polsl.zbdihd.wss.persistence.entity.TransactionEntity;
import pl.polsl.zbdihd.wss.persistence.repository.TransactionEntityRepository;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransactionService extends JobExecutor<TransactionJob> {

    private final TransactionEntityRepository transactionRepository;

    public TransactionService(final ApplicationEventPublisher eventPublisher,
                              final TransactionEntityRepository transactionRepository) {
        super(TableType.TRANSACTION, eventPublisher);
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void execute(final Job<?> job, final int trackId) {
        if (job instanceof TransactionJob transactionJob) {
            execute(transactionJob, trackId);
        } else {
            throw new IllegalArgumentException("Unsupported job: " + job);
        }
    }

    private void execute(final TransactionJob job, final int trackId) {
        final LocalDateTime executionStartTime = LocalDateTime.now();
        final Set<TransactionEntity> transactions = job.records()
                                                       .stream()
                                                       .map(TransactionEntity::valueOf)
                                                       .collect(Collectors.toSet());
        transactionRepository.saveAll(transactions);
        simulateExecution(job, executionStartTime, trackId);
    }

}
