package com.example.githubapiconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiBranchesDTO {
    private String branchName;
    private String lastCommitSha;
}