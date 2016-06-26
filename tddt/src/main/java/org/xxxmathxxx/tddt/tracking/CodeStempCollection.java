/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.ArrayList;

/**
 * @author Tschebyscheff, 25.06.16
 * CodeStempCollection gathers all CodeStempObjects each stage 
 *
 */
public class CodeStempCollection {

	private ArrayList<Object> list = new ArrayList<Object>();
	
	
	public void addCodeStemp(CodeStemp codeStemp){
		list.add(codeStemp);
	}
	
	public CodeStemp getCodeStemp(int i){
		return (CodeStemp) list.get(i);
	}
	
	public CodeStemp getLatestCodeStemp(){
		return (CodeStemp) list.get(list.size());
	}

	public int size(){
		return list.size();
	}
	
	
}