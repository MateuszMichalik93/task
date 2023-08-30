package com.atipera.controllers.dtos;

import java.util.List;
import lombok.Value;

@Value
public class GithubRepoDto {

    String name;
    boolean forked;
    GithubUser owner;
    List<Branch> branches;

    @Value
    public static class GithubUser {
        String login;
    }

    @Value
    public static class Branch {
        String branchName;
        String lastCommitSha;
    }
}
