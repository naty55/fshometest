package com.fshometest2.fshometest.error;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fshometest2.fshometest.wallet.error.InvalidAccessTokenException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler{

    @Autowired
    ObjectMapper objectMapper;
    
    @ExceptionHandler(MissingParameterException.class)
    public ResponseEntity<Object> missingParameter(MissingParameterException ex, WebRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", ex.getClass().getSimpleName());
        map.put("missingParameter", ex.getParameterName());
        map.put("walletId", ex.getWalletId());
        return handleExceptionInternal(ex, map, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(InvalidAccessTokenException.class)
    public ResponseEntity<Object> missingParameter(InvalidAccessTokenException ex, WebRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", ex.getClass().getSimpleName());
        map.put("token", ex.getAccessToken());
        map.put("walletId", ex.getWalletId());
        return handleExceptionInternal(ex, map, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }



    
    
}
