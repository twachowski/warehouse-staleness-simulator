package pl.polsl.zbdihd.wss.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.zbdihd.wss.domain.covid.Voivodeship;
import pl.polsl.zbdihd.wss.persistence.entity.CovidReportEntity;

@Repository
public interface CovidReportEntityRepository extends JpaRepository<CovidReportEntity, Voivodeship> {
}
