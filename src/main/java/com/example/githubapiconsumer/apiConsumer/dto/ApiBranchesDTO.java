package com.example.githubapiconsumer.apiConsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiBranchesDTO {
    private String branchName;
    private String lastCommitSha;
}