package com.atipera.modules.github.domain;

import java.util.List;

public record GithubUserRepository(String name,
                                   boolean fork,
                                   GithubUser owner,
                                   List<Branch> branch) {

    public record GithubUser(String login) { }

    public record Branch( String name, Commit commit){

        public record Commit(String sha){}
    }
}
