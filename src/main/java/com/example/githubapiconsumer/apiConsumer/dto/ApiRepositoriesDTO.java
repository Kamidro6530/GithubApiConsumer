package com.example.githubapiconsumer.apiConsumer.dto;

import com.example.githubapiconsumer.apiConsumer.dto.ApiBranchesDTO;
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
