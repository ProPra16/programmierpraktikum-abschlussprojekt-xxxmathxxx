/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.ArrayList;

/**
 * The Class CodeStampCollection.
 *
 * @author Tschebyscheff, 25.06.16
 * CodeStempCollection gathers all CodeStempObjects each stage 
 */
public class CodeStampCollection {

	/** The list of CodeStamps. */
	private ArrayList<Object> list = new ArrayList<Object>();
	
	
	/**
	 * Adds a code stamp.
	 *
	 * @param codeStamp the code stamp
	 */
	public void addCodeStamp(CodeStamp codeStamp){
		list.add(codeStamp);
	}
	
	/**
	 * Gets the code stamp.
	 *
	 * @param i the index
	 * @return the code stamp
	 */
	public CodeStamp getCodeStemp(int i){
		return (CodeStamp) list.get(i);
	}
	
	/**
	 * Gets the latest code stamp.
	 *
	 * @return the latest code stamp
	 */
	public CodeStamp getLatestCodeStamp(){
		if(list.size()>0)
		{
			return (CodeStamp) list.get(list.size() - 1);
		}
		return null;	
	}

	/**
	 * Size.
	 *
	 * @return the size of the CodeStamp list
	 */
	public int size(){
		return list.size();
	}
}
