package com.example.githubapiconsumer;

import com.example.githubapiconsumer.dto.ApiRepositoriesDTO;
import com.example.githubapiconsumer.service.ApiService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/users/{username}/repos")
    public Flux<ApiRepositoriesDTO> getApiRepositoriesResponse(@PathVariable String username){
        return apiService.getUserRepositoriesAndBranches(username);
    }
}
