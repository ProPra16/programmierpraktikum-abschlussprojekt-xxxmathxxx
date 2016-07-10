package org.xxxmathxxx.tddt.logging;

/**A generic IO exception that logs itself
 * @author xxxMathxxx
 *
 */
@SuppressWarnings("serial")
public class TDDTIOError extends Exception{
	
	/**
	 * The error message as String
	 */
	private String message;
	
	/**Creates a new error message with the given String
	 * @param message The message as String
	 */
	public TDDTIOError(String message){
		this.message = message;
		TDDTLogManager.getInstance().logMessage(message);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage(){
		return "TDDTIOException: "+message+"\n"+getStackTrace().toString();
	}
}
