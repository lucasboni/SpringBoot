package br.com.devdojo.awesome.handler;

import br.com.devdojo.awesome.error.ErroDetails;
import br.com.devdojo.awesome.error.ResorceNotFoundDetails;
import br.com.devdojo.awesome.error.ResorceNotFoundException;
import br.com.devdojo.awesome.error.ValidationErroDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Esta classe pega o evento, de erro e monta o json da maneitra desejada
 */
@ControllerAdvice
public class RestExeptionHandler extends ResponseEntityExceptionHandler {


    @Override  //seria a chamada generica, caso nao tenh tratamento especifico usa este
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErroDetails erroDetails = ErroDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Internal Exeption")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();
        return new ResponseEntity<>(erroDetails,headers,status);
    }

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

    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlermethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        ValidationErroDetails resorceNotFound = ValidationErroDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Fild Validation Error")
                .detail("Fild Validation Error")
                .developerMessage(methodArgumentNotValidException.getClass().getName())
                .field(fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(",")))
                .fieldMessage(fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(",")))
                .build();
        return new ResponseEntity<>(resorceNotFound,HttpStatus.BAD_REQUEST);
    }*/

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        ValidationErroDetails resorceNotFound = ValidationErroDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Fild Validation Error")
                .detail("Fild Validation Error")
                .developerMessage(methodArgumentNotValidException.getClass().getName())
                .field(fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(",")))
                .fieldMessage(fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(",")))
                .build();
        return new ResponseEntity<>(resorceNotFound,HttpStatus.BAD_REQUEST);
    }

    //@Override
    //protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    //    return super.handleMethodArgumentNotValid(ex, headers, status, request);
    //}


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErroDetails erroDetails = ErroDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resorce not found")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();
        return new ResponseEntity<>(erroDetails,HttpStatus.BAD_REQUEST);
    }
}
