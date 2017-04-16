package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
//import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

/**
 * Created by NIC on 4/11/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Data
//@Table(name = "private")
public class RunningInformationAnalysis {

    public enum HealWarningLevel{
        LOW, NORMAL, HIGH;
    }


    @Id
    @GeneratedValue
    private long id;


    private String runningId;

    private double latitude;
    private double longitude;

    private double runningDistance;
    private double totalRunningTime;

    private int heartRate;

    private Date timestamp ;

    private HealWarningLevel healWarningLevel;

    @Embedded
    private final UserInfo userInfo;

    public RunningInformationAnalysis(){
        this.userInfo = null;
    }

    //final string username
    public RunningInformationAnalysis(String username, String address){
        this.userInfo = new UserInfo(username, address);
    }

    @JsonCreator
    public RunningInformationAnalysis(
            @JsonProperty("runningId") String runningId,
            @JsonProperty("latitude") String latitude,
            @JsonProperty("longitude") String longitude,
            @JsonProperty("runningDistance") String runningDistance,
            @JsonProperty("totalRunningTime") String totalRunningTime,
            @JsonProperty("heartRate") String heartRate,
            @JsonProperty("timestamp") String timestamp,
            @JsonProperty("userInfo") UserInfo userInfo
    ){
        this.runningId = runningId;
        this.latitude = Double.parseDouble(latitude);
        this.runningDistance = Double.parseDouble(runningDistance);
        this.totalRunningTime = Double.parseDouble(totalRunningTime);
        this.timestamp = new Date();//每次进来在新建
        this.userInfo = userInfo;

        this.heartRate = _getRandomHeartRate(60, 200);

        if(this.heartRate > 120){
            this.healWarningLevel = HealWarningLevel.HIGH;
        }else if(this.heartRate > 75){
            this.healWarningLevel = HealWarningLevel.NORMAL;
        }else if (this.heartRate >= 60){
            this.healWarningLevel = HealWarningLevel.LOW;
        }else {
            //Intentionally left blank
        }

        System.out.println(this.heartRate);
    }

    public String getUsername(){
                return this.userInfo == null ? null : this.userInfo.getUsername();
    }

    public String getAddress(){
        return this.userInfo == null ? null : this.userInfo.getAddress();
    }

    private int _getRandomHeartRate(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min + 1 );
    }

}
