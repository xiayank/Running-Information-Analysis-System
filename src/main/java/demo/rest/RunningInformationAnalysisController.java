package demo.rest;

import demo.domain.RunningInformationAnalysis;
import demo.service.impl.RunningAnalysisServiceImpl;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RunningInformationAnalysisController {

    private final String kDefaultPage = "0";
    private final String kDefaultItemsPerPage = "3";

    private final String kFieldRuunningId = "runningId";
    private final String kFieldTotalRunningTime = "totalRunningTime";
    private final String kFieldHeartRate = "heartRate";
    private final String kFieldUserId = "id";
    private final String kFieldUserName = "userName";
    private final String kFieldUserAddress = "userAddress";
    private final String kFieldHealWarnningLevel = "healWarnningLevel";

    @Autowired
    private RunningAnalysisServiceImpl runningAnalysisService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void upload(@RequestBody List<RunningInformationAnalysis> runningInformationAnlysesList){


        runningAnalysisService.saveRunningInformationAnalysis(runningInformationAnlysesList);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge(){
        runningAnalysisService.deleteAll();
    }

    @RequestMapping(value = "/heartRate/{heartRate}", method = RequestMethod.GET)
    public Page<RunningInformationAnalysis> findByHeartRate(
            @PathVariable Integer heartRate,
            @RequestParam(name = "page", required = false, defaultValue = kDefaultPage) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = kDefaultItemsPerPage)Integer size
            ){

        return runningAnalysisService.findByHeartRate(heartRate, new PageRequest(page, size));
    }

    @RequestMapping(value = "/heartRateGreatThan/{heartRate}", method = RequestMethod.GET)
    public ResponseEntity<List<JSONObject>> findByHeartRateGreatThan(
            @PathVariable Integer heartRate,
            @RequestParam(name = "page", required = false, defaultValue = kDefaultPage) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = kDefaultItemsPerPage) Integer size
            ){

        Page<RunningInformationAnalysis> rawGreatThanList = runningAnalysisService.findByHeartRateGreaterThan(heartRate, new PageRequest(page, size));
        List<RunningInformationAnalysis> content = rawGreatThanList.getContent();
        //sort list by heartRate
        Collections.sort(content, new Comparator<RunningInformationAnalysis>() {
            @Override
            public int compare(RunningInformationAnalysis o1, RunningInformationAnalysis o2) {
                int x = o1.getHeartRate(), y = o2.getHeartRate();
                return  (x < y) ? -1 : ((x == y) ? 0 : 1);
            }
        });

        List<JSONObject> results = new ArrayList<JSONObject>();
        for(RunningInformationAnalysis item: content){
            JSONObject info = new JSONObject();
            info.put(kFieldRuunningId, item.getRunningId());
            info.put(kFieldTotalRunningTime, item.getTotalRunningTime());
            info.put(kFieldHeartRate, item.getHeartRate());
            info.put(kFieldUserId, item.getId());
            info.put(kFieldUserName, item.getUsername());
            info.put(kFieldUserAddress, item.getUsername());
            info.put(kFieldHealWarnningLevel,item.getHealWarningLevel());

            results.add(info);
        }

        return new ResponseEntity<List<JSONObject>>(results, HttpStatus.OK);

    }


}
