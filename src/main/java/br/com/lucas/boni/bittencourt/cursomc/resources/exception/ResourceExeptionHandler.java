package br.com.lucas.boni.bittencourt.cursomc.resources.exception;

import br.com.lucas.boni.bittencourt.cursomc.services.exception.DataIntegrityException;
import br.com.lucas.boni.bittencourt.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;


//intercepta requisições
@ControllerAdvice
public class ResourceExeptionHandler implements Serializable {

    @ExceptionHandler(ObjectNotFoundException.class)//intercepta uma requisição especifica
    public ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        StandarError standarError = new StandarError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standarError);
    }

    @ExceptionHandler(DataIntegrityException.class)//intercepta uma requisição especifica
    public ResponseEntity<StandarError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
        StandarError standarError = new StandarError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standarError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)//intercepta uma requisição especifica
    public ResponseEntity<StandarError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());

        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
