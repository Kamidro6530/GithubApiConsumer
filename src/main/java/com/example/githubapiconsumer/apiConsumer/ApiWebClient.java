package com.example.githubapiconsumer.apiConsumer;

import com.example.githubapiconsumer.ExceptionObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.NotAcceptableStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
class ApiWebClient {

    WebClient webClient = WebClient.create("https://api.github.com");

    public Flux<ApiRepositoryResponse> getUserRepos(String username) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{username}/repos").build(username))
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        response -> switch (response.statusCode().value()){
                            case 404 -> Mono.error(new ExceptionObject("404","User not found"));
                            case 500 -> Mono.error(new ExceptionObject("500","server error"));
                            default -> Mono.error(new ExceptionObject("Error","something went wrong"));
                        })
                .bodyToFlux(ApiRepositoryResponse.class);
    }

    public Flux<ApiBranchesResponse> getUserRepoBranches(String username,String repoName){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/repos/{owner}/{repo}/branches").build(username,repoName))
                .retrieve()
                .bodyToFlux(ApiBranchesResponse.class);
    }


}
