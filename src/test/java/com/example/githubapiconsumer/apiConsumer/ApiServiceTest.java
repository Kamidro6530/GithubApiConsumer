package com.example.githubapiconsumer.apiConsumer;

import com.example.githubapiconsumer.apiConsumer.dto.ApiRepositoriesDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ApiServiceTest {

    @Mock
    private  ApiWebClient apiWebClient;
    @InjectMocks
    private  ApiService apiService;

    @Test
    void getUserRepositoriesAndBranches() {
        ApiRepositoryResponse repositoryResponse = getApiRepositoryResponse();
        ApiBranchesResponse mockBranchResponse = getApiBranchesResponse();

        Mockito.when(apiWebClient.getUserRepos(any()))
                .thenReturn(Flux.just(repositoryResponse));
        Mockito.when(apiWebClient.getUserRepoBranches(any(), any())).thenReturn(Flux.just(mockBranchResponse));

        Flux<ApiRepositoriesDTO> result = apiService.getUserRepositoriesAndBranches("username");

        StepVerifier.create(result)
                .expectNextMatches(object -> object.getRepoName().equals("repoName"))
                .verifyComplete();
    }

    @Test
    void getUserRepositoriesAndBranchesWhenRepositoryIsFork() {
        ApiRepositoryResponse repositoryResponse = getApiRepositoryResponse();
        repositoryResponse.setFork(true);

        Mockito.when(apiWebClient.getUserRepos(any()))
                .thenReturn(Flux.just(repositoryResponse));

        Flux<ApiRepositoriesDTO> result = apiService.getUserRepositoriesAndBranches("username");

        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();
    }

    private ApiRepositoryResponse getApiRepositoryResponse() {
        ApiRepositoryResponse repositoryResponse = new ApiRepositoryResponse();
        repositoryResponse.setName("repoName");
        repositoryResponse.setOwner(new ApiOwnerResponse());

        return repositoryResponse;
    }

    private ApiBranchesResponse getApiBranchesResponse() {
        ApiBranchesResponse branchesResponse = new ApiBranchesResponse();
        branchesResponse.setCommit(new ApiCommitResponse());

        return branchesResponse;
    }
}