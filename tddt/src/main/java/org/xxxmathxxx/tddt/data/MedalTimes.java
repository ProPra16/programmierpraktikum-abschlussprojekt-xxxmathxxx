package org.xxxmathxxx.tddt.data;

/**Simple data class to make handling times easier and clean-up constructors
 * @author Philipp Spohr, Jul 9, 2016
 *
 */
public class MedalTimes {
	
	public double author;
	public double gold;
	public double silver;
	public double bronze;
	
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
