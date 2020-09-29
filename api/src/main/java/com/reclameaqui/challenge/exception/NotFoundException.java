package com.reclameaqui.challenge.exception;

/** class that represent not found resource exception */
public class NotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
	
	/** class constructor */
    public NotFoundException(String msg) {
		super(msg);
	}
	
	/**class constructor */
	public NotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
