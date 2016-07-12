package org.xxxmathxxx.tddt.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xxxmathxxx.tddt.data.*;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;

/**
 * @author Fabian
 * Reads XML-Exercises from disc
 */
public final class ExerciseReader {

	private static DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
	private static DocumentBuilder builder;
	static{
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static TDDTLogManager logger= TDDTLogManager.getInstance(); //woodcutting confirmed



	/**
	 * Reads all exercises from disk and return them as Collection.
	 * Use this Method.
	 * @return List filled with all saved exercises. Null if no exercises could be found.
	 */
	public static ExerciseCollection readAllExercises()
	{  
		ArrayList<Exercise> exercises= new ArrayList<Exercise>();
		Stream<Path> walk = null;
		FileSystem fileSystem = null;
		try {
			boolean runningFromJar = false;
		    URI uri = ExerciseReader.class.getResource("/exercises").toURI();
		    System.out.println(uri);
		    Path myPath;
		    if (uri.getScheme().equals("jar")) {
		    	System.out.println("RUNNING FROM JAR");
		        fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
		        myPath = fileSystem.getPath("/exercises");
		        runningFromJar = true;
		    } else {
		    	System.out.println("NOT RUNNING FROM JAR");
		        myPath = Paths.get(uri);
		    }
		    walk = Files.walk(myPath, 1);
		    for (Iterator<Path> it = walk.iterator(); it.hasNext();){
		    	Path p = it.next();
		    	if (!p.toString().endsWith(".xml")){
		    		continue;
		    	}
		    	else{
		    		System.out.println(p);
		    	}
		    	File f;
		    	if (runningFromJar){
			    	String absoloutePath = ExerciseReader.class.getResource(p.toString()).toExternalForm();
			    	System.out.println(absoloutePath);
			    	f = new File(absoloutePath);
		    	}
		    	else{
		    		f = p.toFile();
		    	}

		    	if (f.isDirectory()){
		    		continue;
		    	}
		    	System.out.println(f);
				try{
					exercises.add(readExercise(f));
			        TDDTLogManager.getInstance().logMessage("Found exercise: "+f.getAbsolutePath());
				}
				catch(Exception e)
				{
					logger.logMessage("Failed to read Exercise "+f.getName()+". Maybe its not in the right format?");
				}		
			}
		    

	    }

	catch(IOException | URISyntaxException e1)
	{
		e1.printStackTrace();
	}
	finally{
		if ( walk != null){
			walk.close();
		}
		if (fileSystem != null){
			try {
				fileSystem.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	if(!exercises.isEmpty())
	{
		return new ExerciseCollection(exercises);
	}
	
	return null;

}
	
	/**
	 * Reads specified Exercise from Disc
	 * 
	 * @param filename
	 *            Filename with fileextension e.g.: "exercises\RomanNumbers.xml"
	 * @return returns Exercise as Exercise Class
	 * @throws Exception A generic Exception
	 */
	public static Exercise readExercise(File input) throws Exception { 
		Document exercise;
		NodeList pointer;

		try {
			exercise = builder.parse(input);

		} catch (Exception e) 
		{
			TDDTLogManager.getInstance().logMessage("You've screwed up m8. Likely the file you're searching for doesn't exist or is not in xml form. \n"+e.getMessage());
			

			return null;
		}

		// Creating variables for the exercise object
		String name;
		String id;
		String description;

		ExerciseClass[] referencedClasses;
		ExerciseTest[] referencedTests;

		//Fetches name
		pointer= exercise.getElementsByTagName("name");
		
		name=pointer.item(0).getTextContent();
		
		//Fetches id
		pointer= exercise.getElementsByTagName("id");
		
		id=pointer.item(0).getTextContent();
		
		//Fetches description
		pointer= exercise.getElementsByTagName("description");
		
		description=pointer.item(0).getTextContent();

		// Fetches Classes
		pointer = exercise.getElementsByTagName("class");
		referencedClasses = new ExerciseClass[pointer.getLength()];

		for (int i1 = 0; i1 < pointer.getLength(); i1++) {
			String className1 = pointer.item(i1).getAttributes().getNamedItem("name").getTextContent();
			String classCode1 = pointer.item(i1).getTextContent();
			referencedClasses[i1] = new ExerciseClass(className1, new JavaCode(classCode1));

		}

		// Fetches Tests
		pointer = exercise.getElementsByTagName("test");
		referencedTests = new ExerciseTest[pointer.getLength()];

		for (int i1 = 0; i1 < pointer.getLength(); i1++) {
			String testName = pointer.item(i1).getAttributes().getNamedItem("name").getTextContent();
			String testCode = pointer.item(i1).getTextContent();

			referencedTests[i1] = new ExerciseTest(testName, new JavaCode(testCode));
		}

		// Fetches Finishtest ->Extension
		pointer = exercise.getElementsByTagName("finishtest");

		String testName = pointer.item(0).getAttributes().getNamedItem("name").getTextContent();
		String testCode = pointer.item(0).getTextContent();
		ExerciseTest referencedFinishTest = new ExerciseTest(testName, new JavaCode(testCode));

		// ****Fetches config

		// Babysteps
		pointer = exercise.getElementsByTagName("babysteps");

		Boolean babysteps = pointer.item(0).getAttributes().getNamedItem("value").getTextContent().equals("True"); // Babysteps
																													// enabled
																													// BTW:
																													// THIS
																													// SWAGMOVE
		String babystepsTime = pointer.item(0).getAttributes().getNamedItem("time").getTextContent(); // Convert
																										// to
																										// double

		// Timetracking
		pointer = exercise.getElementsByTagName("timetracking");

		Boolean timetracking = pointer.item(0).getAttributes().getNamedItem("value").getTextContent().equals("True");

		// Medals... yeah we totally need it
		pointer = exercise.getElementsByTagName("medals");

		//Read MedalTimes
		double bronzetime = Double.parseDouble(pointer.item(0).getAttributes().getNamedItem("bronze").getTextContent());
		double silvertime = Double.parseDouble(pointer.item(0).getAttributes().getNamedItem("silver").getTextContent());
		double goldtime = Double.parseDouble(pointer.item(0).getAttributes().getNamedItem("gold").getTextContent());
		double authortime = Double.parseDouble(pointer.item(0).getAttributes().getNamedItem("author").getTextContent());
		
		MedalTimes times = new MedalTimes(authortime,goldtime,silvertime,bronzetime);
		Exercise ret = new Exercise(name, (long)Integer.parseInt(id), description, referencedClasses, referencedTests, referencedFinishTest,
				new ExerciseConfig(babysteps, Double.parseDouble(babystepsTime)
						,timetracking
						,times
						));
		//ret.print();
		return ret; // Done!
	}

}
