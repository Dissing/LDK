//============================================================================
// Name        : LDKException
// Author      : Lasse Dissing Hansen
// Version     : 0.1
// Copyright   : (C) 2012 Lasse Dissing Hansen
// Description : 
//============================================================================
package volpes.ldk;

/**
 * A generic error for the LDK game development kit
 * @author Lasse Dissing Hansen
 */
public class LDKException extends Exception {
	
	private static final long serialVersionUID = -1854857273279338810L;

	public LDKException(String message) {
		super(message);
	}
	
	public LDKException(String message, Throwable e) {
		super(message,e);
	}

}
