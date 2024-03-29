package com.example.githubapiconsumer.apiConsumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
class ApiBranchesResponse {
    private String name;
    @JsonProperty(namespace = "commit")
    private ApiCommitResponse commit;
}
