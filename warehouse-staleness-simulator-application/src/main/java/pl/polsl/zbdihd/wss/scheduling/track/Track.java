package pl.polsl.zbdihd.wss.scheduling.track;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import pl.polsl.zbdihd.wss.algorithm.JobOrderingAlgorithm;
import pl.polsl.zbdihd.wss.domain.Job;
import pl.polsl.zbdihd.wss.scheduling.event.NewJobEvent;
import pl.polsl.zbdihd.wss.scheduling.event.WarehouseEvent;
import pl.polsl.zbdihd.wss.scheduling.service.JobExecutor;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

@Slf4j
public class Track implements ApplicationListener<WarehouseEvent> {

    private final int id;
    private final JobOrderingAlgorithm orderingAlgorithm;
    private final Set<JobExecutor<? extends Job<?>>> jobExecutors;
    private final Queue<Job<?>> jobs = new PriorityQueue<>();
    private Job<?> currentJob;

    public Track(final int id,
                 final JobOrderingAlgorithm orderingAlgorithm,
                 final Set<JobExecutor<? extends Job<?>>> jobExecutors) {
        this.id = id;
        this.orderingAlgorithm = orderingAlgorithm;
        this.jobExecutors = jobExecutors;
    }

    @Override
    @Async
    public void onApplicationEvent(final WarehouseEvent event) {
        if (event.getTrackId() == id) {
            if (event.hasNewJob()) {
                handleNewJob(event.asNewJobEvent());
            } else if (event.isJobFinished()) {
                handleJobFinished();
            }
        }
    }

    private void handleNewJob(final NewJobEvent<?> event) {
        if (currentJob == null) {
            log.info("There is no current job, handling new job event {}...", event);
            currentJob = event.getJob();
            executeCurrentJob();
        } else {
            log.info("A job is being executed, adding new job from event {} to queue...", event);
            jobs.add(event.getJob());
            orderingAlgorithm.sort(jobs);
        }
    }

    private void handleJobFinished() {
        currentJob = jobs.poll();
        if (currentJob == null) {
            log.info("Current job execution has finished, no other job to execute...");
        } else {
            log.info("Current job execution has finished, executing next job {}...", currentJob);
            executeCurrentJob();
        }
    }

    private void executeCurrentJob() {
        jobExecutors.stream()
                    .filter(executor -> executor.supports(currentJob))
                    .findFirst()
                    .ifPresent(executor -> executor.execute(currentJob, id));
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Track that) {
            return this.id == that.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
