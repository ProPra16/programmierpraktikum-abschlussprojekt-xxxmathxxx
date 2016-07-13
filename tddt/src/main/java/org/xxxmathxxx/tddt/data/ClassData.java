package org.xxxmathxxx.tddt.data;

/**
 * @author xxxMathxxx
 * Abstract class to interact with ExerciseTest and ExerciseClass
 */
public abstract class ClassData 
{
	public String name;
	public JavaCode code;
	
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
