package demo.service.impl;

import demo.domain.RunningInformationAnalysis;
import demo.domain.RunningInformationAnalysisRepository;
import demo.service.RunningAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NIC on 4/11/17.
 */
@Service
public class RunningAnalysisServiceImpl implements RunningAnalysisService {

    private RunningInformationAnalysisRepository runningInformationAnalysisRepository;

    @Autowired
    public RunningAnalysisServiceImpl(RunningInformationAnalysisRepository runningInformationAnalysisRepository){
        this.runningInformationAnalysisRepository = runningInformationAnalysisRepository;
    }

    @Override
    public List<RunningInformationAnalysis> saveRunningInformationAnalysis(List<RunningInformationAnalysis> runningInformationAnlyses) {
        return runningInformationAnalysisRepository.save(runningInformationAnlyses);
    }

    @Override
    public Page<RunningInformationAnalysis> findByHeartRate(int heartRate, Pageable pageable) {
        return runningInformationAnalysisRepository.findByHeartRate(heartRate,pageable);
    }

    @Override
    public Page<RunningInformationAnalysis> findByHeartRateGreaterThan(int heartRate, Pageable pageable) {
        return runningInformationAnalysisRepository.findByHeartRateGreaterThan(heartRate, pageable);
    }

    @Override
    public Page<RunningInformationAnalysis> findAllRunningInformationOrderByHealthLevel(Pageable pageable) {
        return null;
    }

    @Override
    public void deleteAll() {
        runningInformationAnalysisRepository.deleteAll();
    }
}
