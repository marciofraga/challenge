package com.reclameaqui.challenge.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** class that represent response passed the user when exception ocurred */
@Getter
@Setter
@AllArgsConstructor
public class TemplateError {
    /** HTTP status */
	private Integer status;
	/** datetime when exceptio ocurred  */
	private Long timeStamp;
	/** message detailing the error */
	private String message;
}
