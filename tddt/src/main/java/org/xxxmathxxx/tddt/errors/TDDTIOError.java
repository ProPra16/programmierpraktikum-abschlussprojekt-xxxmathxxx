package org.xxxmathxxx.tddt.errors;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;

@SuppressWarnings("serial")
public class TDDTIOError extends Exception{
	
	private String message;
	
	public TDDTIOError(String message){
		this.message = message;
		TDDTLogManager.getInstance().logMessage(message);
	}
	
	@Override
	public String getMessage(){
		return message+"\n"+getStackTrace();
	}
}
