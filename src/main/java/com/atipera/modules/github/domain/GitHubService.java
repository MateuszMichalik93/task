package com.atipera.modules.github.domain;

import com.atipera.modules.github.exception.UserNotFoundException;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Slf4j
class GitHubService {

    private final GithubPort githubPort;
    WebClient webClient = WebClient.builder().build();

    public List<GithubUserRepository> getGithubRepositories(String userName) throws UserNotFoundException {

        GithubUserRepository[] fetchedRepos = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .scheme("https")
                .host("api.github.com")
                .path("users/{username}/repos")
                .build(userName))
            .header("Accept", "application/json")
            .retrieve()
            .bodyToMono(GithubUserRepository[].class)
            .block();

        if (fetchedRepos == null || fetchedRepos.length == 0) {
            throw new UserNotFoundException("User or repositories not found");
        }

        return Stream.of(fetchedRepos)
            .filter(repo -> !repo.fork())
            .map(repo -> {
                GithubUserRepository.Branch[] branches = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("api.github.com")
                        .path("repos/{owner}/{repo}/branches")
                        .build(userName, repo.name()))
                    .header("Accept", "application/json")
                    .retrieve()
                    .bodyToMono(GithubUserRepository.Branch[].class)
                    .block();

                return new GithubUserRepository(repo.name(), false, repo.owner(), List.of(branches));
            })
            .toList();
    }

}

