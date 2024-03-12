package com.example.githubapiconsumer.apiConsumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class ApiOwnerResponse {
    private String login;
}
