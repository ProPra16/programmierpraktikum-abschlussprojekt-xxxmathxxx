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
	
	/**
	 * Sets an selected profile
	 * @param p New Profile
	 */
	public void setProfile(Profile p){
		instance.profile = p;
	}
	
	/**
	 * Sets an Editor Scene for a started Exercise
	 * @param e New Editor
	 */
	public void setEditor(Editor e){
		this.editor = e;
	}
	
	/**
	 * Private constructor so it can't be constructed from elsewhere.
	 */
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
		//generateStartingStamp(); -> Initial state is Test not Refactor
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
		
		if(profile.getMedalState(currentExercise.id)!=null && newState.ordinal()>profile.getMedalState(currentExercise.id).ordinal())
		{
			profile.setMedalState(currentExercise.id, newState);
		}
		else
		{
			profile.setMedalState(currentExercise.id, newState);
		}
			
	}
	
	/**
	 * Requests a switch to the next state and attempts to perform it.
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
			CompilationUnit[] cuArray= getCompilationUnits(false);
			JavaStringCompiler jsc= CompilerFactory.getCompiler(cuArray);
			trackerManager.atMap.get(CodeStage.REFACTOR).codeStampCollection.addCodeStamp(CodeStamp.generateCodeStamp(jsc,cuArray));
			
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
			babystepsTimer.setActive(false);
			totalTimer.setActive(true);
			
			trackerManager.atMap.get(CodeStage.CODE).setTimerActive(false);
			trackerManager.atMap.get(CodeStage.TEST).setTimerActive(false);
			trackerManager.atMap.get(CodeStage.REFACTOR).setTimerActive(true);
			break;
		}	
	}
	
	/**
	 * Disables all timers. Don't forget to activate them afterwards.
	 */
	private void disableAllTimers()
	{
		babystepsTimer.setActive(false);
		totalTimer.setActive(false);
		
		trackerManager.atMap.get(CodeStage.CODE).setTimerActive(false);
		trackerManager.atMap.get(CodeStage.TEST).setTimerActive(false);
		trackerManager.atMap.get(CodeStage.REFACTOR).setTimerActive(false);
	}
	
	
	/**
	 * Checks if an exercise passes the finishTests. If so it will close the Editor and save stats to the selected profile.
	 */
	public void finalizeExercise()
	{
		editor.tep.save();
		editor.cep.save();
		//Step 0: Check if final test is successful
		CompilationUnit[] cuArray= getCompilationUnits(true);
		JavaStringCompiler jsc= CompilerFactory.getCompiler(cuArray);
		jsc.compileAndRunTests();
		
		if(jsc.getCompilerResult()==null ||jsc.getTestResult()==null || jsc.getTestResult().getNumberOfFailedTests() != 0 || jsc.getCompilerResult().hasCompileErrors())
		{
			AlertMessenger.showErrorMessage("Failure","You haven't finished this task yet!");
			return;
		}
		
		
		babystepsTimer.setActive(false);
		totalTimer.setActive(false);
		TDDTLogManager.getInstance().logMessage("Total time needed for this exercise: "+totalTimer.getTimeInSecondsAsString());
		MedalState medalEarned = currentExercise.checkMedalForTime(totalTimer.getTime());
		if (medalEarned != MedalState.NONE){
	
			awardMedal(medalEarned);

			WindowManager.getInstance().createAchievementPopup(medalEarned);
		}
		//STEP 1: Update stats
		AnalyzedTrackingData dataForThisExercise = new AnalyzedTrackingData(trackerManager);
		
		if(getExercise().config.trackingEnabled)
		{
			boolean saveStats = true;
			if (profile.profileStats.getTrackingData(currentExercise.id) != null){
				saveStats = AlertMessenger.showQuestionMessage("Overwrite stats?", "It seems that you already completed this exercise earlier. Do you want to override the old data?");
			}
			if (saveStats){
				profile.profileStats.addTrackingData(currentExercise.id, dataForThisExercise);
			}
		}
		dataForThisExercise.log();
		
		//STEP 1 Might be sane to save those juicy stats and achievements
		profile.saveProfileToFile();
		
		//STEP 3: Quit the shit out of it
		leave();
	}
	
	/**
	 * Checks if you can switch to codestage
	 * @return True if change is possible, false otherwise
	 */
	private Boolean switchToCode()
	{
		CompilationUnit[] cuArray= getCompilationUnits(false);
		JavaStringCompiler jsc= CompilerFactory.getCompiler(cuArray);
		trackerManager.atMap.get(CodeStage.TEST).codeStampCollection.addCodeStamp(CodeStamp.generateCodeStamp(jsc,cuArray));
		CodeStamp codeStamp = trackerManager.atMap.get(CodeStage.TEST).codeStampCollection.getLatestCodeStamp();
		
		if(codeStamp.getResult().hasNoCompilerErrors()||codeStamp.getResult().oneFailedTest())
		{	
			return true;
		}

		AlertMessenger.showErrorMessage("Could not switch.", codeStamp.getResult().getCompilerErrors(cuArray));
		return false;
	}
	
	/**
	 * Checks if you can switch to refactorstage
	 * @return True if change is possible, false otherwise
	 */
	private Boolean switchToRefactor()
	{
		CompilationUnit[] cuArray= getCompilationUnits(false);
		JavaStringCompiler jsc= CompilerFactory.getCompiler(cuArray);
		trackerManager.atMap.get(CodeStage.CODE).codeStampCollection.addCodeStamp(CodeStamp.generateCodeStamp(jsc,cuArray));
		CodeStamp codeStamp = trackerManager.atMap.get(CodeStage.CODE).codeStampCollection.getLatestCodeStamp();
		
		if(codeStamp.getResult().hasNoCompilerErrors())
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
				AlertMessenger.showErrorMessage("Test Failed!", codeStamp.getResult().getFailedTestMessages());
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
	 */
	public void cancelRequested() {
		TDDTLogManager.getInstance().logMessage("Switching to Test Stage");
		state = CodeStage.TEST;
		babystepsTimer.resetTimer();
		editor.cep.rerollTo(trackerManager.atMap.get(CodeStage.TEST).codeStampCollection.getLatestCodeStamp().getCompilationUnits());
	}

	/**
	 * Reverts code to last Stamp.
	 */
	public void performBabystepRevert() {
		if(getExercise().config.babystepsEnabeled)
		{
			switch(state)
			{
			case TEST:
				//It is possible that you took forever to write the first test, in this case there is nowhere to roll back to
				if (trackerManager.atMap.get(CodeStage.REFACTOR).codeStampCollection.getLatestCodeStamp() != null){
					CompilationUnit[] rollBackCUnits = trackerManager.atMap.get(CodeStage.REFACTOR).codeStampCollection.getLatestCodeStamp().getCompilationUnits();
					editor.tep.rerollTo(rollBackCUnits);
				}
				TDDTLogManager.getInstance().logMessage("Babysteps time is up but there is no CodeStamp to go back to yet!");
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
	 * Leaves the current exercise
	 */
	private void leave() {
		TDDTLogManager.getInstance().logMessage("Editor gets resetted.");
		WindowManager.getInstance().showMenu(MenuType.EXERCISEPICKER);
	}

	/**
	 * Gives you current state as CodeStage
	 * @return the state
	 */
	public CodeStage getState() {
		return state;
	}

	/**
	 * Gets called by the Editor if a quit is intended
	 */
	public void exitRequest() {
		if(AlertMessenger.showQuestionMessage("Really quit?", "You're missing a lot of fun!"))
		{
			if(AlertMessenger.showQuestionMessage("Are you serious???", "Oh come on man, you can't be serious!"))
			{
				if(!AlertMessenger.showQuestionMessage("Ok...", "We've worked hard on this. Do you really not want to quit?"))
				{
					leave();
				}
			}
		}
	}

	
}
