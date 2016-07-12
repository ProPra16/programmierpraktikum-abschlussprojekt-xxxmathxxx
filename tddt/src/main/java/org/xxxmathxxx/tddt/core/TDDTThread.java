package org.xxxmathxxx.tddt.core;

import org.xxxmathxxx.tddt.data.CodeStage;
import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.gui.AlertMessenger;
import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.gui.WindowManager.MenuType;
import org.xxxmathxxx.tddt.gui.ide.CodeEditPane;
import org.xxxmathxxx.tddt.gui.ide.TestEditPane;
import org.xxxmathxxx.tddt.gui.scenes.Editor;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.MedalState;
import org.xxxmathxxx.tddt.profile.Profile;
import org.xxxmathxxx.tddt.timer.BabystepsTimer;
import org.xxxmathxxx.tddt.timer.BasicTimer;
import org.xxxmathxxx.tddt.tracking.CodeStamp;
import org.xxxmathxxx.tddt.tracking.TrackerManager;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedTrackingData;

import vk.core.api.CompilationUnit;
import vk.core.api.CompilerFactory;
import vk.core.api.JavaStringCompiler;

/**This class describes the running program and its state. That means it includes information about the current profile, the exercise and the trackers.
 * @author xxxMathxxx 2016
 */
public class TDDTThread {
	
	/**
	 * This is the only instance ever existing. It is only accessed internally.
	 */
	private static TDDTThread instance;
	
	/**This is your way from outside this class to access the running Thread
	 * @return The active TDDTThread, if none exists one is created upon invocation.
	 */
	public static TDDTThread getInstance(){
		if (instance == null){
			instance = new TDDTThread();
		}
		return instance;
	}
	
	/** The active profile. */
	private Profile profile;
	
	/** The current exercise. */
	private Exercise currentExercise;

	/**The active TrackerManager
	 * @see TrackerManager
	 */
	public TrackerManager trackerManager;
	
	/**
	 * The babystepsTimer that counts down the babysteps time if the extension is active.
	 */
	public BabystepsTimer babystepsTimer;
	
	/**The timer that tracks the total time elapsed on one exercise.
	 * 
	 */
	public BasicTimer totalTimer;
	
	/** The current state of the Thread, 
	 *  @see CodeStage 
	 */
	private CodeStage state;
	
	/**The editor in which the current Thread is running. This reference is needed to send update hooks to the editor (so it can visualize when a stage change has occured)
	 * 
	 */
	private Editor editor;
	
	public void setProfile(Profile p){
		instance.profile = p;
	}
	
	public void setEditor(Editor e){
		this.editor = e;
	}
	
	private TDDTThread(){}
	
	/**
	 * Initalizes values
	 */
	public void initialize()
	{
		state =CodeStage.TEST;
		trackerManager = new TrackerManager();
		babystepsTimer = new BabystepsTimer();
		totalTimer = new BasicTimer();
	}
	
	/**
	 * Begins a given exercise.
	 *
	 * @param ex the ex
	 */
	public void beginExercise(Exercise ex){
		this.currentExercise = ex;
		this.babystepsTimer.setActive(true);
		this.totalTimer.setActive(true);
		generateStartingStamp();
		TDDTThread.getInstance().trackerManager.getTrackerForStage(CodeStage.TEST).setTimerActive(true);
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
	private void awardMedal(MedalState newState) {
		profile.setMedalState(currentExercise.id, newState);
	}
	
	/**
	 * generates an initial codestamp in refractor
	 */
	private void generateStartingStamp()
	{
		Exercise ex=getExercise();
		
		CompilationUnit[] cuArray= new CompilationUnit[ex.referencedClasses.length+ex.referencedTests.length];
		
		for(int i=0; i<ex.referencedClasses.length;i++)
		{
			cuArray[i]=new CompilationUnit(ex.referencedClasses[i].name, ex.referencedClasses[i].code.rawText, false);
		}
		
		for(int i=0; i<ex.referencedTests.length;i++)
		{
			cuArray[ex.referencedClasses.length+i]=new CompilationUnit(ex.referencedTests[i].name, ex.referencedTests[i].code.rawText, true);
		}
		
		JavaStringCompiler jsc= CompilerFactory.getCompiler(cuArray);
		trackerManager.atMap.get(CodeStage.REFACTOR).codeStampCollection.addCodeStamp(CodeStamp.generateCodeStamp(jsc,cuArray));
	}
	
	/**Requests a switch to the next state and attempts to perform it.
	 * @param editor The editor from which the switch is called. (This is unelegant as f*** btw, we should simply pass the compilation units)
	 */
	public void requestSwitch() {
		TDDTLogManager.getInstance().logMessage("Stateswitch requested");
		//Saves all Changes
		editor.tep.save();
		editor.cep.save();
		
		disableAllTimers();
		
		switch(getState())
		{
		case TEST: //Switch to code (RED->green)
			if(switchToCode()) //Checks if exacly one Test fails/ the program does not compile
			{
				TDDTLogManager.getInstance().logMessage("Switching to Code Stage");
				state =CodeStage.CODE;
			}
			break;
		case CODE: //Switch to refactor (GREEN->Refactor)
			if(switchToRefactor()) //TODO: Test if code compiles and no test are failing
			{
				TDDTLogManager.getInstance().logMessage("Switching to Refactor Stage");
				state =CodeStage.REFACTOR;
			}
			break;
		case REFACTOR: //Switch to test (refactor->red)
			TDDTLogManager.getInstance().logMessage("Switching to Test Stage");
			state =CodeStage.TEST;
		}	
		
		updateTimers();
	}
	
	/**
	 * Updates Timers after a Statechange happened
	 */
	private void updateTimers()
	{
		switch(getState())
		{
		case TEST: //Switch to code (RED->green)
			babystepsTimer.setActive(true);
			totalTimer.setActive(true);
			
			trackerManager.atMap.get(CodeStage.CODE).setTimerActive(false);
			trackerManager.atMap.get(CodeStage.TEST).setTimerActive(true);
			trackerManager.atMap.get(CodeStage.REFACTOR).setTimerActive(false);
			break;
		case CODE: //Switch to refactor (GREEN->Refactor)
			babystepsTimer.setActive(true);
			totalTimer.setActive(true);
			
			trackerManager.atMap.get(CodeStage.CODE).setTimerActive(true);
			trackerManager.atMap.get(CodeStage.TEST).setTimerActive(false);
			trackerManager.atMap.get(CodeStage.REFACTOR).setTimerActive(false);
			break;
		case REFACTOR: //Switch to test (refactor->red)
			babystepsTimer.setActive(true);
			totalTimer.setActive(true);
			
			trackerManager.atMap.get(CodeStage.CODE).setTimerActive(false);
			trackerManager.atMap.get(CodeStage.TEST).setTimerActive(false);
			trackerManager.atMap.get(CodeStage.REFACTOR).setTimerActive(true);
			break;
		}	
	}
	
	private void disableAllTimers()
	{
		babystepsTimer.setActive(false);
		totalTimer.setActive(false);
		
		trackerManager.atMap.get(CodeStage.CODE).setTimerActive(false);
		trackerManager.atMap.get(CodeStage.TEST).setTimerActive(false);
		trackerManager.atMap.get(CodeStage.REFACTOR).setTimerActive(false);
	}
	
	
	public void finalizeExercise()
	{
		editor.tep.save();
		editor.cep.save();
		//Step 0: Check if final test is successful
		CompilationUnit[] cuArray= getCompilationUnits(true);
		JavaStringCompiler jsc= CompilerFactory.getCompiler(cuArray);
		jsc.compileAndRunTests();
		
		if(jsc.getTestResult().getNumberOfFailedTests() != 0 || jsc.getCompilerResult().hasCompileErrors())
		{
			AlertMessenger.showErrorMessage("Failure","You haven't finished this task yet!");
			return;
		}
		
		
		babystepsTimer.setActive(false);
		totalTimer.setActive(false);
		TDDTLogManager.getInstance().logMessage("Total time needed for this exercise: "+babystepsTimer.getTimeInSecondsAsString());
		MedalState medalEarned = currentExercise.checkMedalForTime(totalTimer.getTime());
		if (medalEarned != MedalState.NONE){
			awardMedal(medalEarned);
			WindowManager.getInstance().createAchievementPopup(medalEarned);
		}
		//Step 1: Check total time
		//trackerManager.getTotalTime(); -> Method doesn't exist
		
		//STEP 2: Update stats
		AnalyzedTrackingData dataForThisExercise = new AnalyzedTrackingData(trackerManager);
		profile.profileStats.addTrackingData(currentExercise.id, dataForThisExercise);
		dataForThisExercise.log();
		
		//STEP 3: Quit the shit out of it
		reset();
	}
	
	/**Attempts to switch to code state
	 * @return True if change is succesful, false otherwise
	 */
	private Boolean switchToCode()
	{
		CompilationUnit[] cuArray= getCompilationUnits(false);
		JavaStringCompiler jsc= CompilerFactory.getCompiler(cuArray);
		trackerManager.atMap.get(CodeStage.TEST).codeStampCollection.addCodeStamp(CodeStamp.generateCodeStamp(jsc,cuArray));
		CodeStamp codeStamp = trackerManager.atMap.get(CodeStage.TEST).codeStampCollection.getLatestCodeStamp();
		
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
		trackerManager.atMap.get(CodeStage.CODE).codeStampCollection.addCodeStamp(CodeStamp.generateCodeStamp(jsc,cuArray));
		CodeStamp codeStamp = trackerManager.atMap.get(CodeStage.CODE).codeStampCollection.getLatestCodeStamp();
		
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
		CodeEditPane cep = editor.cep; //shortcut
		TestEditPane tep = editor.tep;
		
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
	 * @param editor
	 */
	public void cancelRequested() {
		TDDTLogManager.getInstance().logMessage("Switching to Test Stage");
		state = CodeStage.TEST;
		babystepsTimer.resetTimer();
		editor.cep.rerollTo(trackerManager.atMap.get(CodeStage.TEST).codeStampCollection.getLatestCodeStamp().getCompilationUnits());
	}

	public void performBabystepRevert() {
		if(getExercise().config.babystepsEnabeled)
		{
			switch(state)
			{
			case TEST:
				//editor.tep.rerollChanges();
				editor.tep.rerollTo(trackerManager.atMap.get(CodeStage.REFACTOR).codeStampCollection.getLatestCodeStamp().getCompilationUnits());
				break;
				
			case CODE:
				//editor.cep.rerollChanges();
				editor.cep.rerollTo(trackerManager.atMap.get(CodeStage.TEST).codeStampCollection.getLatestCodeStamp().getCompilationUnits());
				break;
	
			case REFACTOR:	
				break;
			}
		}		
	}

	/**
	 * Resets everything.
	 */
	private void reset() {
		TDDTLogManager.getInstance().logMessage("Editor gets resetted.");
		WindowManager.getInstance().showMenu(MenuType.EXERCISEPICKER);
		state =CodeStage.TEST;
	}

	/**
	 * @return the state
	 */
	public CodeStage getState() {
		return state;
	}

	/**
	 * Gets called by the Editor if a quit is intended
	 */
	public void exitRequest() {
		if(AlertMessenger.showQuestionMessage("You are missing a lot of fun!", "Really quit?"))
		{
			if(AlertMessenger.showQuestionMessage("Oh come on, you cant be serious...", "Really???"))
			{
				if(AlertMessenger.showQuestionMessage("We worked hard on this...", "Ok"))
				{
					reset();
				}
			}
		}
	}

	
}
