package br.com.lucas.boni.bittencourt.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandarError {

    private List<FieldMessage> list = new ArrayList<>();

    public ValidationError(Integer estatus, String msg, Long timestamp) {
        super(estatus, msg, timestamp);
    }

    public List<FieldMessage> getErrors() {
        return list;
    }

    public void addError(String fieldName, String message) {
        this.list.add(new FieldMessage(
                fieldName,
                message
        ));
    }
}
