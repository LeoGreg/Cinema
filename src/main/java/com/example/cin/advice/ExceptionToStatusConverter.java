package com.example.cin.advice;

import com.example.cin.model.exception.DuplicateException;
import com.example.cin.model.exception.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
@ControllerAdvice
public class ExceptionToStatusConverter {


    @ResponseBody
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String duplicate(DuplicateException e) {
        log.info(">>>" + e.getMessage() + ":");
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String duplicate(NotFoundException e) {
        log.info(">>>" + e.getMessage() + ":");
        return e.getMessage();
    }
}
