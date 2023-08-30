package com.atipera.controllers.dtos;

import lombok.Value;

@Value
public class BranchDto {
    String branchName;
    String lastCommitSha;
}
