package org.xxxmathxxx.tddt.logging;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class TDDTLogManager {
		
		private static String logFolderPath = "logs";
		
		private static Logger log;
		private static TDDTLogManager instance;
		private static FileHandler fh;
		
		
		
		private TDDTLogManager(){

			log = Logger.getGlobal();
			
			//Create log folder if it doesn't exist yet
			File logDirectory =  new File(logFolderPath);
			if (!logDirectory.exists()){
				logDirectory.mkdirs();
				logMessage("Log folder doesn't exist yet, creating one");
			}
			
			try {
				String dateID = ""+
						Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"."+
						(Calendar.getInstance().get(Calendar.MONTH)+1)+"."+
						Calendar.getInstance().get(Calendar.YEAR)+"#"+
						Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+"h"+
						Calendar.getInstance().get(Calendar.MINUTE)+"m"+
						Calendar.getInstance().get(Calendar.SECOND)+"s"
						;
				fh = new FileHandler("logs/"+dateID+".log");
		        fh.setFormatter(new LogFormatter());  
		        log.addHandler(fh);
		        log.setUseParentHandlers(false);
		        logMessage("Created a logfile for this lauch at location: "+"logs/"+dateID+".log");
			} catch (SecurityException | IOException e) {
				System.out.println("Couldn't create a logfile for this launch! Errors will not be logged!");
				e.printStackTrace();
			}  


		}
		
		public static TDDTLogManager getInstance() {
			
			if (instance == null) {
				instance = new TDDTLogManager();
			}
			
			return instance;
		}
		
		public void logMessage(String thisOne){
			System.out.println(thisOne);
			String entry = "[" + Calendar.getInstance().getTime().toString() + "]: " + thisOne;
			log.info(entry);
		}

		public void cleanup() {
			fh.close();
		}


}
