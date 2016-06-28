package org.xxxmathxxx.tddt.io;

import java.io.File;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xxxmathxxx.tddt.data.ClassData;
import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.data.ExerciseClass;
import org.xxxmathxxx.tddt.data.ExerciseConfig;
import org.xxxmathxxx.tddt.data.ExerciseTest;
import org.xxxmathxxx.tddt.data.JavaCode;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;

public class ExerciseReader {

	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	TDDTLogManager logger; //Huehue like woodcutting

	/**
	 * Creates new ExerciseReader. You have to initialise this class!
	 */
	public ExerciseReader() {
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();

			logger = TDDTLogManager.getInstance();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Reads Exercise from Disc
	 * 
	 * @param filename
	 *            Filename with fileextension e.g.: "RomanNumbers.xml"
	 * @return returns Exercise as Exercise Class
	 */
	public Exercise readExercise(String filename) {
		File input = new File("exercises/" + filename);
		Document exercise;
		NodeList pointer;

		try {
			exercise = builder.parse(input);

		} catch (Exception e) 
		{
			TDDTLogManager.getInstance().logMessage("You've screwed up m8. Likely the file you're searching for doesn't exist or is not in xml form.");


			return null;
		}

		// Creating variables for the exercise object
		String name;
		String id;
		String description;

		ExerciseClass[] referencedClasses;
		ExerciseTest[] referencedTests;
		ExerciseTest[] referencedFinishTests;

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
		referencedFinishTests = new ExerciseTest[pointer.getLength()];

		for (int i = 0; i < pointer.getLength(); i++) {
			String testName = pointer.item(i).getAttributes().getNamedItem("name").getTextContent();
			String testCode = pointer.item(i).getTextContent();

			referencedFinishTests[i] = new ExerciseTest(testName, new JavaCode(testCode));
		}

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

		String bronzetime = pointer.item(0).getAttributes().getNamedItem("bronze").getTextContent(); // Convert
																										// these
																										// three
		String silvertime = pointer.item(0).getAttributes().getNamedItem("silver").getTextContent();
		String goldtime = pointer.item(0).getAttributes().getNamedItem("gold").getTextContent();

		return new Exercise(name, id, description, referencedClasses, referencedTests, referencedFinishTests,
				new ExerciseConfig(babysteps, Double.parseDouble(babystepsTime), timetracking,
						Double.parseDouble(bronzetime), Double.parseDouble(silvertime), Double.parseDouble(goldtime))); // Done!
	}

}
