package org.xxxmathxxx.tddt.data;

/**
 * @author Fabian
 * Abstract class to interact with ExerciseTest and ExerciseClass
 */
public abstract class ClassData 
{
	String name;
	JavaCode code;
	
	/**
	 * @param name Name of the class
	 * @param code Code of the class
	 */
	public ClassData(String name, JavaCode code)
	{
		this.name=name;
		this.code=code;
	}
}
