package org.xxxmathxxx.tddt.data;

/**This stores a set of medal times as double (times that are required to receive an achievement)
 * Simple data class to make handling times easier and clean-up constructors.
 * @author xxxMathxxx 2016
 *
 */
public class MedalTimes {
	
	/**
	 * The time that is required to get the (hidden) author medal
	 */
	public double author;
	/**
	 * The time that is required to get the gold medal
	 */
	public double gold;
	/**
	 * The time that is required to get the silver medal
	 */
	public double silver;
	/**
	 * The time that is required to get the bronze medal
	 */
	public double bronze;
	
	/**Default constructor, creates a new object, that
	 * saves times to award medals.
	 * @param author authortime
	 * @param gold goldtime
	 * @param silver silvertime
	 * @param bronze bronzetime
	 */
	public MedalTimes(double author, double gold, double silver, double bronze){
		this.author = author;
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
	}
	
	@Override
	public String toString(){
		String ret = "";
		ret = ret+"Author Medal: "+author;
		ret = ret+"Gold Medal: "+gold;
		ret = ret+"Silver Medal: "+silver;
		ret = ret+"Bronze Medal: "+bronze;
		return ret;
	}
}
