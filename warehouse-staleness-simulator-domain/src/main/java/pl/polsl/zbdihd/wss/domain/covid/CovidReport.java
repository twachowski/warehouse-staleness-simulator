package pl.polsl.zbdihd.wss.domain.covid;

import pl.polsl.zbdihd.wss.domain.Versionable;

import java.time.LocalDateTime;

public record CovidReport(Voivodeship voivodeship,
                          int infections,
                          int deaths,
                          int vaccinations,
                          LocalDateTime versionDateTime) implements Versionable {
}
