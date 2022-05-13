package pl.polsl.zbdihd.wss.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.zbdihd.wss.persistence.entity.TransactionEntity;

@Repository
public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Long> {
}
