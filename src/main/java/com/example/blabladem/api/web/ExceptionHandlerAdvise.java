package com.example.blabladem.api.web;

import com.example.blabladem.api.web.io.ErrorMessage;
import com.example.blabladem.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvise extends ResponseEntityExceptionHandler {

    @Value("#{new java.lang.Boolean(${blabladem.exception.returnExtendedInfo})}")
    private boolean extExceptionInfo;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorMessage handleAllUnhandledExceptions(Throwable e) {
        log.error("Unhandled exception", e);
        return new ErrorMessage("Internal server error", filterExtendedInfo(e));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorMessage suchGroupExistException(BadRequestException e) {
        log.warn("Handle {}, message:[{}].", e.getClass().getSimpleName(), e.getMessage());
        return new ErrorMessage(e.getMessage());
    }

    private Throwable filterExtendedInfo(Throwable throwable){
        return extExceptionInfo ?throwable:null;
    }
}
