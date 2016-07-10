package org.xxxmathxxx.tddt.core;

import java.util.Iterator;

import org.xxxmathxxx.tddt.data.CodeStage;
import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.gui.AlertMessenger;
import org.xxxmathxxx.tddt.gui.ide.CodeEditPane;
import org.xxxmathxxx.tddt.gui.ide.TestEditPane;
import org.xxxmathxxx.tddt.gui.scenes.Editor;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.MedalState;
import org.xxxmathxxx.tddt.profile.Profile;
import org.xxxmathxxx.tddt.tracking.Tracker;

import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.CompilerFactory;
import vk.core.api.JavaStringCompiler;

/**
 * The Class TDDTThread.
 */
public class TDDTThread {
	
	/** The user. */
	private Profile user;
	
	/** The current exercise. */
	private Exercise currentExercise;
	
	public Tracker tracker;
	
	public CodeStage state;
	
	/**
	 * Instantiates a new TDDT thread.
	 *
	 * @param user the user
	 */
	public TDDTThread(Profile user){
		this.user = user;
		this.tracker = new Tracker();
		state = CodeStage.TEST;
	}
	
	/**
	 * Begin exercise.
	 *
	 * @param ex the ex
	 */
	public void beginExercise(Exercise ex){
		this.currentExercise = ex;
		//TODO: Reset timers whatever do stuff
	}
	
	/**
	 * Gets the exercise.
	 *
	 * @return the exercise
	 */
	public Exercise getExercise(){
		//getter because exercise shouldn't be modified directly
		return currentExercise;
	}
	
	/**
	 * Gets the user profile.
	 *
	 * @return the user profile
	 */
	public Profile getUserProfile(){
		//getter because exercise shouldn't be modified directly
		return user;
	}

	/**
	 * Award medal.
	 *
	 * @param exerciseID the exercise ID
	 * @param newState the new state
	 */
	public void awardMedal(Long exerciseID, MedalState newState) {
		user.setMedalState(exerciseID, newState);
	}

	public boolean requestSwitch(CodeStage state, Editor ed) {
		Tracker tracker = TDDT.currentThread.tracker; //shortcut
		switch(state)
		{
		case TEST: //Switch to code (RED->green)
			if(switchToCode(ed)) //Checks if exacly one Test fails
			{
				TDDTLogManager.getInstance().logMessage("Switching to Code Stage");
				tracker.stageRed.stopTimeTracking();
				tracker.stageGreen.startTimeTracking();
				state=CodeStage.CODE;
				return true;
			}
			break;
		case CODE: //Switch to refactor (GREEN->Refactor)
			if(switchToRefactor(ed)) //TODO: Test if code compiles and no test are failing
			{
				TDDTLogManager.getInstance().logMessage("Switching to Refactor Stage");
				tracker.stageGreen.stopTimeTracking();
				tracker.stageRefactor.startTimeTracking();
				state=CodeStage.REFACTOR;
				return true;
			}
			break;
		case REFACTOR: //Switch to test (refactor->red)
			TDDTLogManager.getInstance().logMessage("Switching to Test Stage");
			tracker.stageGreen.startTimeTracking();
			tracker.stageRefactor.stopTimeTracking();
			state=CodeStage.TEST;
			return true;
		}	
		return false;
	}

	
	//TODO: IMPORTANT!!!!!! switchToCode() and switchToRefactor() are BOTH probably somehow already in TSCHEBYSCHEFFS TRAKTOR so we might have redundant duplicate code here
	
	
	/**
	 * Checks if one failed test is present
	 * @return
	 */
	private Boolean switchToCode(Editor ed)
	{
		CompilationUnit[] cuArray= getCompilationUnits(ed);
		
		JavaStringCompiler jsc= CompilerFactory.getCompiler(cuArray);
		
		jsc.compileAndRunTests();
		
		String retardedFishFrogString="";
		
		if(jsc.getCompilerResult().hasCompileErrors())
		{	
			retardedFishFrogString=retardedFishFrogString+"CompileErrors found: \n";
			
			for(int i=0; i<cuArray.length; i++)
			{
				Iterator<CompileError> errors=jsc.getCompilerResult().getCompilerErrorsForCompilationUnit(cuArray[i]).iterator();
						
				while(errors.hasNext())
				{
					retardedFishFrogString=retardedFishFrogString+((CompileError) errors.next()).getMessage();
				}
			}
			AlertMessenger.showErrorMessage("Compile-error", retardedFishFrogString);
			return false;
		}
		else
		{
			if(jsc.getTestResult().getNumberOfFailedTests()==1)
			{
				return true;
			}
			else
			{
				retardedFishFrogString=retardedFishFrogString+"More/ Less than 1 one Test failed.";
				AlertMessenger.showErrorMessage("Test Failed!", retardedFishFrogString);
			}
		}
		
		return false;
	}
	
	private Boolean switchToRefactor(Editor ed)
	{
		CompilationUnit[] cuArray= getCompilationUnits(ed);
		
		JavaStringCompiler jsc= CompilerFactory.getCompiler(cuArray);
		
		jsc.compileAndRunTests();
		
		String retardedFishFrogString="";
		
		if(jsc.getCompilerResult().hasCompileErrors())
		{	
			retardedFishFrogString=retardedFishFrogString+"CompileErrors found: \n";
			
			for(int i=0; i<cuArray.length; i++)
			{
				Iterator<CompileError> errors=jsc.getCompilerResult().getCompilerErrorsForCompilationUnit(cuArray[i]).iterator();
						
				while(errors.hasNext())
				{
					retardedFishFrogString=retardedFishFrogString+((CompileError) errors.next()).getMessage();
				}
			}
			
			AlertMessenger.showErrorMessage("Test Failed!", retardedFishFrogString);
			return false;
		}
		else
		{
			if(jsc.getTestResult().getNumberOfFailedTests()==0)
			{
				return true;
			}
			else
			{
				retardedFishFrogString=retardedFishFrogString+"There are failed tests.";
				AlertMessenger.showErrorMessage("Test Failed!", retardedFishFrogString);
			}
		}
		
		return false;
	}
	
	/**
	 * Creates an Array of CompilationUnits to start compiling.
	 * @return Array of CompilationUnits
	 */
	private CompilationUnit[] getCompilationUnits(Editor ed)
	{
		CodeEditPane cep = ed.cep; //shortcut
		TestEditPane tep = ed.tep;
		
		int addedLength=cep.classdata.length+tep.classdata.length;
		
		CompilationUnit[] cuArray= new CompilationUnit[addedLength];
		
		for(int i=0; i<cep.classdata.length;i++)
		{
			cuArray[i]=new CompilationUnit(cep.classdata[i].name, cep.classdata[i].code.rawText, false);
		}
		
		for(int i=cep.classdata.length; i<addedLength;i++)
		{
			cuArray[i]=new CompilationUnit(tep.classdata[i-cep.classdata.length].name, tep.classdata[i-cep.classdata.length].code.rawText, true);
		}
		
		return cuArray;
	}
	
}
