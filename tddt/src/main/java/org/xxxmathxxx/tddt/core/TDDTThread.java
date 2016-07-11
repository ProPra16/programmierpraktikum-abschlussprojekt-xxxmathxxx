package org.xxxmathxxx.tddt.core;


import org.xxxmathxxx.tddt.data.CodeStage;
import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.gui.AlertMessenger;
import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.gui.ide.CodeEditPane;
import org.xxxmathxxx.tddt.gui.ide.TestEditPane;
import org.xxxmathxxx.tddt.gui.scenes.Editor;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.MedalState;
import org.xxxmathxxx.tddt.profile.Profile;
import org.xxxmathxxx.tddt.tracking.CodeStamp;
import org.xxxmathxxx.tddt.tracking.Tracker;

import vk.core.api.CompilationUnit;
import vk.core.api.CompilerFactory;
import vk.core.api.JavaStringCompiler;

/**
 * The Class TDDTThread.
 */
public class TDDTThread {
	
	private static TDDTThread instance;
	
	public static TDDTThread getInstance(){
		if (instance == null){
			instance = new TDDTThread();
		}
		return instance;
	}
	
	/** The profile. */
	private Profile profile;
	
	/** The current exercise. */
	private Exercise currentExercise;
	
	/** The active tracker */
	public Tracker tracker;
	
	/** The current state of the Thread, 
	 *  @see CodeStage 
	 */
	public CodeStage state;
	
	private Editor ed;
	
	public void setProfile(Profile p){
		instance.profile = p;
	}
	
	public void setEditor(Editor e){
		this.ed = e;
	}
	
	private TDDTThread(){
		state = CodeStage.TEST;
		tracker = new Tracker();
	}
	
	/**
	 * Begins a given exercise.
	 *
	 * @param ex the ex
	 */
	public void beginExercise(Exercise ex){
		this.currentExercise = ex;
		this.tracker.babystepsTimer.toggleActive();
	}
	
	/**
	 * Gets the exercise currently worked on.
	 * @return the exercise
	 */
	public Exercise getExercise(){
		//getter because exercise shouldn't be modified directly
		return currentExercise;
	}
	
	/**
	 * Gets the profile profile.
	 *
	 * @return the profile profile
	 */
	public Profile getUserProfile(){
		//getter because profile shouldn't be modified directly/ is set only in the constructor
		return profile;
	}

	/**
	 * Award medal.
	 *
	 * @param newState the new state
	 */
	public void awardMedal(MedalState newState) {
		profile.setMedalState(currentExercise.id, newState);
	}
	
	
	/**Requests a switch to the next state and attempts to perform it.
	 * @param ed The editor from which the switch is called. (This is unelegant as f*** btw, we should simply pass the compilation units)
	 * @return True if the change is performed, false otherwise
	 */
	public boolean requestSwitch() {
		switch(state)
		{
		case TEST: //Switch to code (RED->green)
			if(switchToCode()) //Checks if exacly one Test fails/ the program does not compile
			{
				TDDTLogManager.getInstance().logMessage("Switching to Code Stage");
				tracker.stageRed.stopTimeTracking();
				tracker.stageGreen.startTimeTracking();
				state=CodeStage.CODE;
				ed.cep.createBackup();
				return true;
			}
			break;
		case CODE: //Switch to refactor (GREEN->Refactor)
			if(switchToRefactor()) //TODO: Test if code compiles and no test are failing
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
	
	
	
	public void finalizeExercise()
	{
		//Step 0: Check if final test is successful
		CompilationUnit[] cuArray= getCompilationUnits(true);
		JavaStringCompiler jsc= CompilerFactory.getCompiler(cuArray);
		CodeStamp codeStamp = CodeStamp.generateCodeStamp(jsc,cuArray);

		if(codeStamp.getResult().getNumberOfFailedTests() != 0)
		{
			AlertMessenger.showErrorMessage("Failure","You haven't finished this task yet!");
			return;
		}
		
		this.tracker.babystepsTimer.toggleActive();
		TDDTLogManager.getInstance().logMessage("Total time needed for this exercise: "+this.tracker.babystepsTimer.getTimeInSecondsAsString());
		MedalState medalEarned = currentExercise.checkMedalForTime(this.tracker.babystepsTimer.getTime());
		if (medalEarned != MedalState.NONE){
			awardMedal(medalEarned);
			WindowManager.getInstance().createAchievementPopup(medalEarned);
		}
		//Step 1: Check total time
		//tracker.getTotalTime(); -> Method doesn't exist
	}
	
	/**Attempts to switch to code state
	 * @return True if change is succesful, false otherwise
	 */
	private Boolean switchToCode()
	{
		CompilationUnit[] cuArray= getCompilationUnits(false);
		JavaStringCompiler jsc= CompilerFactory.getCompiler(cuArray);
		tracker.stageRed.codeStampCollection.addCodeStamp(CodeStamp.generateCodeStamp(jsc,cuArray));
		CodeStamp codeStamp = tracker.stageRed.codeStampCollection.getLatestCodeStamp();
		
		if(codeStamp.getResult().compilerError()||codeStamp.getResult().oneFailedTest())
		{	
			return true;
		}

		AlertMessenger.showErrorMessage("Could not switch.", codeStamp.getResult().getCompilerErrors(cuArray));
		return false;
	}
	
	private Boolean switchToRefactor()
	{
		CompilationUnit[] cuArray= getCompilationUnits(false);
		JavaStringCompiler jsc= CompilerFactory.getCompiler(cuArray);
		tracker.stageGreen.codeStampCollection.addCodeStamp(CodeStamp.generateCodeStamp(jsc,cuArray));
		CodeStamp codeStamp = tracker.stageGreen.codeStampCollection.getLatestCodeStamp();
		
		if(codeStamp.getResult().compilerError())
		{	
			AlertMessenger.showErrorMessage("Test Failed!", codeStamp.getResult().getCompilerErrors(cuArray));
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
				AlertMessenger.showErrorMessage("Test Failed!", "There are failed tests.");
			}
		}
		return false;
	}
	
	
	//TODO: ISNT THIS DUPLICATE TOO WHEN WE ALREADY HAVE THE TRACKER ADDED TO THIS THREAD?
	/**
	 * Creates an Array of CompilationUnits to start compiling.
	 * @return Array of CompilationUnits
	 */
	private CompilationUnit[] getCompilationUnits(boolean withFinalTest)
	{
		CodeEditPane cep = ed.cep; //shortcut
		TestEditPane tep = ed.tep;
		
		int addedLength=cep.classdata.length+tep.classdata.length;
		if (withFinalTest){
			addedLength++;
		}
		
		CompilationUnit[] cuArray= new CompilationUnit[addedLength];
		
		for(int i=0; i<cep.classdata.length;i++)
		{
			cuArray[i]=new CompilationUnit(cep.classdata[i].name, cep.classdata[i].code.rawText, false);
		}
		
		for(int i=0; i<tep.classdata.length;i++)
		{
			cuArray[cep.classdata.length+i]=new CompilationUnit(tep.classdata[i].name, tep.classdata[i].code.rawText, true);
		}
		if (withFinalTest){
			cuArray[cuArray.length-1] = new CompilationUnit(
					currentExercise.referencedFinishTest.name,
					currentExercise.referencedFinishTest.code.rawText,
					true);
		}
		
		return cuArray;
	}

	/**
	 * Is called when the Users requests to cancel from Coding to Testing stage
	 * @param ed
	 */
	public void cancelRequested() {
		TDDTLogManager.getInstance().logMessage("Switching to Test Stage");
		tracker.stageGreen.stopTimeTracking();
		tracker.stageRed.startTimeTracking();
		state=CodeStage.TEST;
		ed.cep.rerollChanges();
	}

	public void performBabystepRevert() {
		switch(state)
		{
		case TEST:
			ed.tep.rerollChanges();
			break;
			
		case CODE:
			ed.cep.rerollChanges();
			
			break;
			
		}
		
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
}
