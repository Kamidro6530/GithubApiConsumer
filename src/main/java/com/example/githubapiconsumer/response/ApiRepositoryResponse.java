package com.example.githubapiconsumer.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiRepositoryResponse {
    private String name;
    @JsonProperty("owner")
    private ApiOwnerResponse owner;
    private String branches_url;

}
