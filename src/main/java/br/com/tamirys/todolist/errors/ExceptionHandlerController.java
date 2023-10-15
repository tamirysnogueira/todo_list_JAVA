package br.com.tamirys.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// ControllerAdvice é uma anotação do spring para definir classes globais quando há o tratamento de exceções
@ControllerAdvice
public class ExceptionHandlerController {
    
    //qual o tipo de exceção
    @ExceptionHandler(HttpMessageNotReadableException.class)

    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMostSpecificCause().getMessage());
    }
}
