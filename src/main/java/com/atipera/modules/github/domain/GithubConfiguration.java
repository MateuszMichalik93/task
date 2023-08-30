package com.atipera.modules.github.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubConfiguration {

    @Bean
    GithubFacade githubFacade(GithubPort port) {
        return new GithubFacade(new GitHubService(port));
    }
}
