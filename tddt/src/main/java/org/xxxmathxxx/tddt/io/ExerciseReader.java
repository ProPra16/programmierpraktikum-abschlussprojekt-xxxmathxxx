package org.xxxmathxxx.tddt.io;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.data.ExerciseClass;
import org.xxxmathxxx.tddt.data.ExerciseTest;
import org.xxxmathxxx.tddt.data.ExerciseTests;
import org.xxxmathxxx.tddt.data.JavaCode;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;

public class ExerciseReader {

	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	TDDTLogManager logger; //Huehue like woodcutting

	public ExerciseReader() {
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			
			
			logger= TDDTLogManager.getInstance();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Exercise readExercise(String filename)
	{
		File input = new File("exercises/"+filename);
		Document exercise;
		NodeList pointer;
		
		try {
			exercise = builder.parse(input);
		} catch (Exception e) 
		{
			TDDTLogManager.getInstance().logMessage("You've screwed up m8. Likely the file you're searching for doesn't exist or is not in xml form.");
			return null;
		}
		
		//Creating variables for the exercise object
		String name;
		String id;
		String description;
		ExerciseClass[] referencedClasses;
		ExerciseTest[] referencedTests;

		Boolean babysteps;
		Boolean timetracking;
		
		//TODO: Import Medal timings
		
//->Some missing
		
		//All variables created
		
		
		
		//Fetches name
		 pointer= exercise.getElementsByTagName("name");
		
		name=pointer.item(0).getTextContent();
		
		//Fetches id
		pointer= exercise.getElementsByTagName("id");
		
		id=pointer.item(0).getTextContent();
		
		//Fetches description
		pointer= exercise.getElementsByTagName("description");
		
		description=pointer.item(0).getTextContent();
		
		//Fetches Classes
		pointer= exercise.getElementsByTagName("class");
		referencedClasses= new ExerciseClass[pointer.getLength()];
		
		for(int i=0; i<pointer.getLength();i++)
		{
			String className=pointer.item(i).getAttributes().getNamedItem("name").getTextContent();
			String classCode= pointer.item(i).getTextContent();
			
			referencedClasses[i]= new ExerciseClass(className, new JavaCode(classCode));
		}
		
		//Fetches Tests
		pointer= exercise.getElementsByTagName("test");
		referencedTests= new ExerciseTest[pointer.getLength()];
		
		for(int i=0; i<pointer.getLength();i++)
		{
			String testName=pointer.item(i).getAttributes().getNamedItem("name").getTextContent();
			String testCode= pointer.item(i).getTextContent();
			
			referencedTests[i]= new ExerciseTest(testName, new JavaCode(testCode));
		}
		
		
		return new Exercise(name, id, description, referencedClasses, referencedTests); //Test
	}
	
	
	
}
