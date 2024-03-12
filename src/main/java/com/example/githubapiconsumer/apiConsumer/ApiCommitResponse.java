package com.example.githubapiconsumer.apiConsumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
class ApiCommitResponse {
    private String sha;
}
