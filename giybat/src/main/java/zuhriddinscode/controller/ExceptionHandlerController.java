package zuhriddinscode.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import zuhriddinscode.exps.AppBadException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle (AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}