package br.com.devdojo.awesome.handler;

import br.com.devdojo.awesome.error.ResorceNotFoundDetails;
import br.com.devdojo.awesome.error.ResorceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * Esta classe pega o evento, de erro e monta o json da maneitra desejada
 */
@ControllerAdvice
public class RestExeptionHandler {
    @ExceptionHandler(ResorceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundExeption(ResorceNotFoundException rnfException){
        ResorceNotFoundDetails resorceNotFound = ResorceNotFoundDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resorce not found")
                .detail(rnfException.getMessage())
                .developerMessage(rnfException.getClass().getName())
                .build();
        return new ResponseEntity<>(resorceNotFound,HttpStatus.NOT_FOUND);
    }
}
