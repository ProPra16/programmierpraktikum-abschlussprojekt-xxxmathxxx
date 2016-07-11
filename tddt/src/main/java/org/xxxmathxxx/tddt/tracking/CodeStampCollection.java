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
	public void addCodeStemp(CodeStamp codeStamp){
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
		return (CodeStamp) list.get(list.size() - 1);
	}

	/**
	 * Size.
	 *
	 * @return the size of the CodeStamp list
	 */
	public int size(){
		return list.size();
	}
	
	/**
	 * Removes the code stamp.
	 *
	 * @param i the index
	 */
	public void removeCodeStamp(int i){
		list.remove(i);
	}
	
	/**
	 * Sets the code stamp to i in list.
	 *
	 * @param i the index
	 * @param codeStamp the code stamp
	 */
	public void setCodeStamp(int i, CodeStamp codeStamp){
		list.set(i, codeStamp);
	}
	
	/**
	 * Index of a CodeStamp.
	 *
	 * @param codeStamp the code stamp
	 * @return the index of the CodeStamp in the list
	 */
	public int indexOf(CodeStamp codeStamp){
		return list.indexOf(codeStamp);
	}
	
	
}
