package com.example.githubapiconsumer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionObject extends RuntimeException{
    String status;
    String message;
}
