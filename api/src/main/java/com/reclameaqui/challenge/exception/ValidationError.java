package com.reclameaqui.challenge.exception;

import java.util.HashMap;

import lombok.Getter;

/**  class that represent response passed the user when MethodArgumentNotValid exception ocurred */
@Getter
public class ValidationError extends TemplateError {

    /** list with all fields with error */
    private HashMap<String, String> errors = new HashMap<String, String>();

    /** class constructor */
    public ValidationError(Integer status, Long timeStamp, String message) {
        super(status, timeStamp, message);
    }
    
    /** class constructor */
    public void addError(String fieldName, String message) {
        errors.put(fieldName, message);
    }
}
