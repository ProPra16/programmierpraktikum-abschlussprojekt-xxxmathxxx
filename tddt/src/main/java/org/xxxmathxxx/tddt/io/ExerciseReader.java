package org.xxxmathxxx.tddt.io;

import java.io.File;
import java.util.ArrayList;

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
		File file = new File("src/main/resources/exercises");
		File[] files = file.listFiles();
		
		ArrayList<Exercise> exercises= new ArrayList<Exercise>();
		
		for(int i=0; i<files.length;i++)
		{
			try
			{
				exercises.add(readExercise(files[i].getPath()));
			}
			catch(Exception e)
			{
				logger.logMessage("Failed to read Exercise "+files[i].getPath()+". Maybe its not in the right format?");
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
	public static Exercise readExercise(String filename) throws Exception { 
		File input = new File(filename);
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
		
		return new Exercise(name, (long)Integer.parseInt(id), description, referencedClasses, referencedTests, referencedFinishTest,
				new ExerciseConfig(babysteps, Double.parseDouble(babystepsTime)
						,timetracking
						,times
						)); // Done!
	}

}
