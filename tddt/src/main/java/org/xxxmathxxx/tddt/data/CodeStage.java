package org.xxxmathxxx.tddt.data;

/**Enum to determine CodeStage of running thread
 * @author xxxMathxxx 2016
 * 
 * @see TDDTThread
 */
public enum CodeStage {
	/**
	 * Also called Stage Red, this is the Stage in which the user writes tests
	 */
	TEST,
	/**
	 * Also called Stage Green, this is the Stage in which the user writes code
	 */
	CODE,
	/**
	 * This describes the refactor stage
	 */
	REFACTOR
}
