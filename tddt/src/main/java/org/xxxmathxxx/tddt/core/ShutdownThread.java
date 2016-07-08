package org.xxxmathxxx.tddt.core;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;

/**
 * @author philipp
 * Simple class that describes a thread which is invoked upon termination
 * Thus any clean-up operations may find their home here
 */
public class ShutdownThread extends Thread {
	@Override
	public void run() {
		TDDTLogManager.getInstance().logMessage("Terminating the program ...");
		//Cleaning the log manager, this should be the last clean-up step so the others can still be logged
		TDDTLogManager.getInstance().logMessage("Cleaning up log-system");
		TDDTLogManager.getInstance().cleanup();
		//
	}
}
