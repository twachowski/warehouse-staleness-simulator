package pl.polsl.zbdihd.wss.scheduling.event;

import pl.polsl.zbdihd.wss.domain.covid.CovidJob;
import pl.polsl.zbdihd.wss.domain.covid.CovidReport;

public class CovidReportEvent extends WarehouseEvent<CovidReport> {

    public CovidReportEvent(final CovidJob job, final int trackId) {
        super(job, trackId);
    }

}
