package com.atipera.modules.github.infrastructure;

import com.atipera.modules.github.domain.GithubPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubInfrastructureConfiguration {

    @Bean
    GithubPort githubPort() {
        return new GithubRepositoryJDBC();
    }
}
