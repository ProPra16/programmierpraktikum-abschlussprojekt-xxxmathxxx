package org.xxxmathxxx.tddt.core;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;

public class ShutdownThread extends Thread {
	@Override
	public void run() {
		TDDTLogManager.getInstance().logMessage("Terminating the program ...");
		//
		TDDTLogManager.getInstance().logMessage("Cleaning up log-system");
		TDDTLogManager.getInstance().cleanup();
	}
}
