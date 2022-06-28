package pl.polsl.zbdihd.wss.algorithm;

import pl.polsl.zbdihd.wss.domain.Job;

import java.util.Queue;

public interface JobOrderingAlgorithm {

    void sort(Queue<Job<?>> jobs);

}
