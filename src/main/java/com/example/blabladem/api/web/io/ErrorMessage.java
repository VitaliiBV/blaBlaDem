package com.example.blabladem.api.web.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.exception.ExceptionUtils;

@Data
public class ErrorMessage {

    private String cause;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ExceptionInfo exception;

    public ErrorMessage(String msg) {
        this.cause = msg;
    }

    public ErrorMessage(String msg, Throwable e) {
        this.cause = msg;
        if(e!=null){
            this.exception = new ExceptionInfo(e.getClass().getName(),
                    e.getMessage(),
                    ExceptionUtils.getStackTrace(e)
            ) ;
        }
    }
}
