package zuhriddinscode.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import zuhriddinscode.exps.AppBadException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<String> handle (AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle (RuntimeException e){
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}