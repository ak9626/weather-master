package com.example.university.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * ClassName: UniversityResponse
 * Package: com.example.university.pojo
 * Description:
 *
 * @author Fan Peng
 * Create 2025/1/12 19:58
 * @version 1.0
 */
@Data
public class UniversityResponse {
    private List<String> domains;

    @JsonProperty("alpha_two_code")
    private String alphaTwoCode;

    @JsonProperty("web_pages")
    private List<String> webPages;

    private String country;
    private String name;

    @JsonProperty("state-province")
    private String stateProvince;
}
