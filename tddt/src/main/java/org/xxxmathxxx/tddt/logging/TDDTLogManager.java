package org.xxxmathxxx.tddt.logging;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**A simple log-manager that is connected to a file-handler and writes occuring events/errors to a textfile
 * @author xxxMathxxx
 */
public class TDDTLogManager {
		
		/**The relative path to the folder where logs should be stored. This is static and might be changed in a different version to a more dynamic approach.
		 * 
		 */
		private static String logFolderPath = "logs";
		
		/**The java logger object associated with this system
		 * 
		 */
		private static Logger log;
		
		/**TDDTLogManager is a pseudo-singleton class, there is only one instance possible. This points to the only instance available internally.
		 * 
		 */
		
		private static TDDTLogManager instance;
		
		/**The file-handler connected to the logger
		 * 
		 */
		private static FileHandler fh;
		
		/**Default constructor, notice that this is private and is impossible to call from outside.
		 * If you want to access this class you should call getInstance() instead.
		 */
		private TDDTLogManager(){
			
			//Create reference to Logger (just abbreviation)
			log = Logger.getGlobal();
			
			//Create log folder if it doesn't exist yet
			File logDirectory =  new File(logFolderPath);
			
			if (!logDirectory.exists()){
				logDirectory.mkdirs();
				logMessage("Log folder doesn't exist yet, creating one");
			}
			
			//Connect filehandler to the logger, use date and time of launch as filename
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
		
		/**This is the way to access the log manager from outside the class.
		 * It always returns the only instance existing and if none exists it is created internally.
		 * @return The only TDDTLogManager there is
		 */
		public static TDDTLogManager getInstance() {
			
			if (instance == null) {
				instance = new TDDTLogManager();
			}
			
			return instance;
		}
		
		/**This function logs a message and prints it to console.
		 * @param thisOne The message as String
		 */
		public void logMessage(String thisOne){
			System.out.println(thisOne);
			String entry = "[" + Calendar.getInstance().getTime().toString() + "]: " + thisOne;
			log.info(entry);
		}

		/**This function should be called before the program is terminated. It closes the file-handler.
		 * 
		 */
		public void cleanup() {
			fh.close();
			//Here system.out.println is used because the log is already closed.
			System.out.println("log-handler closed!");
		}


}
