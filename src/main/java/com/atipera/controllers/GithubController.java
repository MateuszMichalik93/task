package com.atipera.controllers;

import com.atipera.modules.github.domain.GithubFacade;
import com.atipera.modules.github.domain.GithubUserRepository;
import com.atipera.modules.github.exception.UserNotFoundException;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GithubController {

    private final GithubFacade githubFacade;

    @GetMapping("/github/{username}/repos")
    public ResponseEntity<?> getUserRepository(@PathVariable String username,
                                               @RequestHeader(value = "Accept") String acceptHeader) {
        List<GithubUserRepository> githubRepo = null;
        try {
            githubRepo = githubFacade.getGithubRepository(username);

            if (!"application/json".equals(acceptHeader)) {
                return new ResponseEntity<>(Map.of("status", 406, "Message", "Only application/json is supported"), HttpStatus.NOT_ACCEPTABLE);
            }

            return new ResponseEntity<>(githubRepo, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(Map.of("status", 404, "Message", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}

