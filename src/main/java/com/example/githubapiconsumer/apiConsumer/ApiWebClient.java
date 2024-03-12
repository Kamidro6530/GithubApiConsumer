package com.example.githubapiconsumer.apiConsumer;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
class ApiWebClient {

    WebClient webClient = WebClient.create("https://api.github.com");

    public Flux<ApiRepositoryResponse> getUserRepos(String username) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{username}/repos").build(username))
                .retrieve()
                .bodyToFlux(ApiRepositoryResponse.class);
    }

    public Flux<ApiBranchesResponse> getUserRepoBranches(String username,String repoName){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/repos/{owner}/{repo}/branches").build(username,repoName))
                .retrieve()
                .bodyToFlux(ApiBranchesResponse.class);
    }

}
