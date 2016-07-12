package org.xxxmathxxx.tddt.core;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;

/**
 * @author xxxMathxxx 2016
 * Simple class that describes a thread which is invoked upon termination
 * Thus any clean-up operations may find their home here
 */
public class ShutdownThread extends Thread {
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		TDDTLogManager.getInstance().logMessage("Terminating the program ...");
		//Saving current profile if one exists
		if (TDDTThread.getInstance() != null){
			TDDTLogManager.getInstance().logMessage("Saving current profile");
			TDDTThread.getInstance().getUserProfile().saveProfileToFile();
		}
		//Killing Thread operations that are maybe still pending
		if (TDDTThread.getInstance() != null){

			TDDTThread.getInstance().reset();
		}
		//Cleaning the log manager, this should be the last clean-up step so the others can still be logged
		TDDTLogManager.getInstance().logMessage("Cleaning up log-system");
		TDDTLogManager.getInstance().cleanup();
	}
}
