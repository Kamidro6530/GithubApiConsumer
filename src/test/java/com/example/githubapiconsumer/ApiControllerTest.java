package com.example.githubapiconsumer;

import com.example.githubapiconsumer.apiConsumer.ApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.NotAcceptableStatusException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ApiControllerTest {

    MockMvc mvc;

    @Mock
    private ApiService apiService;
    @InjectMocks
    private ApiController apiController;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(apiController).build();
    }

    @Test
    void handleExceptionObject() throws Exception {
        Mockito.when(apiService.getUserRepositoriesAndBranches("testUsername")).thenThrow(new ExceptionObject("404", "User not found"));

        mvc.perform(get("/users/{username}/repos", "testUsername"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("404"))
                .andExpect(jsonPath("$.message").value("User not found"));
    }

    @Test
    void handleHttpMediaTypeNotAcceptableException() throws Exception {
        Mockito.when(apiService.getUserRepositoriesAndBranches("testUsername")).thenThrow(new NotAcceptableStatusException("error"));

        mvc.perform(get("/users/{username}/repos", "testUsername"))
                .andExpect(status().isNotAcceptable())
                .andExpect(jsonPath("$.status").value("406"))
                .andExpect(jsonPath("$.message").value("Not acceptable format"));
    }
}