package demo.service;

import demo.domain.RunningInformationAnalysis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

/**
 * Created by NIC on 4/11/17.
 */

public interface RunningAnalysisService {

    public List<RunningInformationAnalysis>saveRunningInformationAnalysis(List<RunningInformationAnalysis> runningInformationAnlyses);

    public Page<RunningInformationAnalysis>findByHeartRate(int heartRate, Pageable pageable);

    public Page<RunningInformationAnalysis>findByHeartRateGreaterThan(int heartRate, Pageable pageable);

    public Page<RunningInformationAnalysis>findAllRunningInformationOrderByHealthLevel(Pageable pageable);

    public void deleteAll();

    public List<RunningInformationAnalysis>findAll();

}
