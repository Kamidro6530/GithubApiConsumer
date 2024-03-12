package com.example.githubapiconsumer.apiConsumer;


import com.example.githubapiconsumer.apiConsumer.ApiWebClient;
import com.example.githubapiconsumer.apiConsumer.dto.ApiBranchesDTO;
import com.example.githubapiconsumer.apiConsumer.dto.ApiRepositoriesDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ApiService {

    private final ApiWebClient webClient;

    public ApiService(ApiWebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<ApiRepositoriesDTO> getUserRepositoriesAndBranches(String username){
        return webClient.getUserRepos(username).filter(repo -> !repo.isFork)
                .flatMap(repo -> webClient.getUserRepoBranches(username,repo.getName())
                .map(branch -> new ApiBranchesDTO(branch.getName(), branch.getCommit().getSha()))
                .collectList()
                .map(branches -> new ApiRepositoriesDTO(repo.getName(),repo.getOwner().getLogin(),branches )));
    }
}
