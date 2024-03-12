package com.example.githubapiconsumer.dto;

import com.example.githubapiconsumer.response.ApiOwnerResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApiRepositoriesDTO {
    private String repoName;
    private String repoOwner;
    private List<ApiBranchesDTO> branchesData;


}
