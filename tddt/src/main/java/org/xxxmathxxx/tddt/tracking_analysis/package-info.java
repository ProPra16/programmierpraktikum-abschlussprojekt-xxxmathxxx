
/**
 * @author Tschebyscheff, 30.06.16
 *
 *This package analyzes and stores tracking-data in all 3 Stages(StageGreen, StageRed,StageRefactor)
 *Surface class is AnalyzedTrackingData.
 *It exists a AnalyedTrackingDataCollection. 
 *To create new AnalyzedData simply generate a new AnalyzedTrackingData Object.
 *Tracking-datas are: Keystrokes, Time, Errors(CompileError(different types) TestFailures)
 *
 *TODO: add more identifier for different CompileErrors. You can simply add this in the class AnalyzeError in 
 * the method analyzeCompileError.
 * 
 * WARNING: Dont FORGET to put new Error-Types in the Class Error, if you find
 * new Classifications for CompilerErrors.
 *
 */
package org.xxxmathxxx.tddt.tracking_analysis;