package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by NIC on 4/11/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
@RequiredArgsConstructor
public class UserInfo {

    private String username;
    private String address;




    @JsonCreator
    public UserInfo(
            @JsonProperty("username") String username,
            @JsonProperty("address")  String address) {
        this.username = username;
        this.address = address;
    }
}
