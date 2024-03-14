package com.example.githubapiconsumer;

import com.example.githubapiconsumer.apiConsumer.dto.ApiRepositoriesDTO;
import com.example.githubapiconsumer.apiConsumer.ApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.NotAcceptableStatusException;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/users/{username}/repos")
    public Flux<ApiRepositoriesDTO> getApiRepositoriesResponse(@PathVariable String username){
        return apiService.getUserRepositoriesAndBranches(username);
    }

    @ExceptionHandler(ExceptionObject.class)
    public ResponseEntity<Map<String, String>> handleExceptionObject(ExceptionObject ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("status", ex.getStatus());

        return ResponseEntity
                .status(HttpStatus.valueOf(Integer.parseInt(ex.getStatus())))
                .body(errorDetails);
    }

    @ExceptionHandler(NotAcceptableStatusException.class)
    public ResponseEntity<Map<String, String>> handleHttpMediaTypeNotAcceptableException() {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message","Not acceptable format");
        errorDetails.put("status", "406");

        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDetails);
    }

}
