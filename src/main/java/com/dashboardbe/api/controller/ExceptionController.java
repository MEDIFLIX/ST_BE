package com.dashboardbe.api.controller;

import com.dashboardbe.common.exception.SessionBadRequestException;
import com.dashboardbe.common.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    /**
     * SessionCheckAOP 예외 처리
     */
    @ExceptionHandler(SessionBadRequestException.class)
    public ResponseEntity<ExceptionResponse> sessionBadRequest(final SessionBadRequestException exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(
                        HttpStatus.UNAUTHORIZED.value()
                        ,"NO_LOGIN"),
                HttpStatus.UNAUTHORIZED
        );
    }
}
