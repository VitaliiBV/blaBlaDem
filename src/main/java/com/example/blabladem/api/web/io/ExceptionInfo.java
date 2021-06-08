package com.example.blabladem.api.web.io;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionInfo {
    private String className;
    private String message;
    private String stackTrace;
}
