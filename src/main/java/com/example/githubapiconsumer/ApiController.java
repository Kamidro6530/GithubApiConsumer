package com.example.githubapiconsumer;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class ApiController {

    private final ApiWebClient webClient;

    public ApiController(ApiWebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/users/{username}/repos")
    public Flux<String> getRepositories(@PathVariable String username){
        return webClient.getUserRepos(username);
    }
}
