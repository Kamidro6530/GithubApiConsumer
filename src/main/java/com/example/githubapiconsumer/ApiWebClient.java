package com.example.githubapiconsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class ApiWebClient {

    WebClient webClient = WebClient.create("https://api.github.com");

    public Flux<String> getUserRepos(String username) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{username}/repos").build(username))
                .retrieve()
                .bodyToFlux(String.class);
    }

}
