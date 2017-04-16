package demo.service.impl;

import demo.domain.RunningInformationAnalysis;
import demo.domain.RunningInformationAnalysisRepository;
import demo.service.RunningAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        Page<RunningInformationAnalysis> rawList = runningInformationAnalysisRepository.findAllByOrderByHeartRate(pageable);
//        Page allInfo = new PageImpl(rawList, pageable, 10);
//        return allInfo


        return rawList;
    }

    @Override
    public void deleteAll() {
        runningInformationAnalysisRepository.deleteAll();
    }

    @Override
    public List<RunningInformationAnalysis> findAll() {
        List<RunningInformationAnalysis> rawList = runningInformationAnalysisRepository.findAll();
        Collections.sort(rawList, (o1, o2) -> o1.getHeartRate() - o2.getHeartRate());
        return rawList;
    }
}
