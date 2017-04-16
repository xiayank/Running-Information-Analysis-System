package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


/**
 * Created by NIC on 4/11/17.
 */

public interface RunningInformationAnalysisRepository extends JpaRepository<RunningInformationAnalysis, Long> {
        Page<RunningInformationAnalysis> findByHeartRateGreaterThan(
                @Param("heartRate")int heartRate,
                Pageable pageable
        );

        Page<RunningInformationAnalysis>findByHeartRate(
                @Param("heartRate")int heartRate,
                Pageable pageable
        );

        Page<RunningInformationAnalysis>findAllByOrderByHeartRate(Pageable pageabe);
}
