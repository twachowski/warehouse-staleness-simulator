package pl.polsl.zbdihd.wss.scheduling.event;

import pl.polsl.zbdihd.wss.domain.covid.CovidJob;
import pl.polsl.zbdihd.wss.domain.covid.CovidReport;

public class CovidReportJobEvent extends NewJobEvent<CovidReport> {

    public CovidReportJobEvent(final CovidJob job, final int trackId) {
        super(job, trackId);
    }

}
