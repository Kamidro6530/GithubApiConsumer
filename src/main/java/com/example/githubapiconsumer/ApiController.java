package com.example.githubapiconsumer;

import com.example.githubapiconsumer.response.ApiRepositoryResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class ApiController {

    private final ApiWebClient webClient;

    public ApiController(ApiWebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/users/{username}/repos")
    public Flux<ApiRepositoryResponse> getRepositories(@PathVariable String username){
        return webClient.getUserRepos(username);
    }
}
