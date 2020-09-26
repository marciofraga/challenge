package com.reclameaqui.challenge.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TemplateError {
    
    private Integer status;
	private Long timeStamp;
	private String message;
}
