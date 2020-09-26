package com.reclameaqui.challenge.exception;

import java.util.HashMap;

import lombok.Getter;

@Getter
public class ValidationError extends TemplateError {

    private HashMap<String, String> errors = new HashMap<String, String>();

    public ValidationError(Integer status, Long timeStamp, String message) {
        super(status, timeStamp, message);
    }
    
    public void addError(String fieldName, String message) {
        errors.put(fieldName, message);
    }
}
