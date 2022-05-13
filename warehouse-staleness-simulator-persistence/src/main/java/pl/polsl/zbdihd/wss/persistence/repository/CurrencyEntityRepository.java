package pl.polsl.zbdihd.wss.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyCode;
import pl.polsl.zbdihd.wss.persistence.entity.CurrencyEntity;

@Repository
public interface CurrencyEntityRepository extends JpaRepository<CurrencyEntity, CurrencyCode> {
}
