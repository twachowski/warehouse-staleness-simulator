package pl.polsl.zbdihd.wss.persistence.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.polsl.zbdihd.wss.domain.Versionable;
import pl.polsl.zbdihd.wss.domain.covid.CovidReport;
import pl.polsl.zbdihd.wss.domain.covid.Voivodeship;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "COVID_REPORT")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CovidReportEntity implements Versionable {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 4)
    private Voivodeship voivodeship;

    @Column(nullable = false)
    private Integer infections;

    @Column(nullable = false)
    private Integer deaths;

    @Column(nullable = false)
    private Integer vaccinations;

    @Column(nullable = false)
    private LocalDateTime versionDateTime;

    @Override
    public LocalDateTime versionDateTime() {
        return versionDateTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof CovidReportEntity that) {
            return this.voivodeship == that.voivodeship;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return voivodeship.hashCode();
    }

    @Override
    public String toString() {
        return "CovidReportEntity{" +
                "voivodeship=" + voivodeship +
                ", infections=" + infections +
                ", deaths=" + deaths +
                ", vaccinations=" + vaccinations +
                ", versionDateTime=" + versionDateTime +
                '}';
    }

    public static CovidReportEntity valueOf(final CovidReport covidReport) {
        return new CovidReportEntity(covidReport.voivodeship(),
                                     covidReport.infections(),
                                     covidReport.deaths(),
                                     covidReport.vaccinations(),
                                     covidReport.versionDateTime());
    }

}
