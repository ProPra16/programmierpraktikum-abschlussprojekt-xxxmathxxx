package org.xxxmathxxx.tddt.data;

/**Abstract class to interact with ExerciseTest and ExerciseClass
 * @author xxxMathxxx
 */
public abstract class ClassData 
{
	/**
	 * The name of the class as String
	 */
	public String name;
	/**
	 * The code (class body) as JavaCode
	 */
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
