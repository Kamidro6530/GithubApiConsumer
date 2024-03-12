package com.example.githubapiconsumer;

import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @GetMapping("/users/{username}/repos")
    public String getRepositories(@PathVariable String username){
        return "200";
    }
}
