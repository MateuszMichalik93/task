package com.atipera.modules.github.domain;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GithubFacade {

    private final GitHubService gitHubService;

    public List<GithubUserRepository> getGithubRepository(String userName) {
        return gitHubService.getGithubRepositories(userName);
    }
}
