package org.xxxmathxxx.tddt.gui;
import java.io.IOException;
import java.util.ArrayList;

import org.xxxmathxxx.tddt.core.TDDTThread;
import org.xxxmathxxx.tddt.data.CodeStage;
import org.xxxmathxxx.tddt.io.ExerciseReader;
import org.xxxmathxxx.tddt.profile.Profile;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedStage;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedTrackingData;
import org.xxxmathxxx.tddt.tracking_analysis.ErrorType;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Tschebyscheff
 * TODO: handle MenuButton(ActionEvent) and backButton
 *
 */
public class StatisticsPane extends Pane{ //suggestion: move this to gui package and add only the scene that contains this to gui.scenes

	private Profile profile;
	
	
	AnalyzedTrackingData analyzedTrackingData;
	
	private  XYChart.Series<String, Integer> series1;
	private  XYChart.Series<String, Integer> series2;
	private  XYChart.Series<String, Integer> series3;
	private  XYChart.Series<String, Integer> series4;
	private  XYChart.Series<String, Integer> series5;
	private  XYChart.Series<String, Integer> series6;
	private  XYChart.Series<String, Integer> series7;
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private Label profileLabel;
	@FXML
	private PieChart pieChart;
	@FXML
	private MenuButton menuButton;
	
	private ExerciseComboBox exPick;
    
   
	public StatisticsPane(){
		this.profile = TDDTThread.getInstance().getUserProfile();
				
		
		try {
			AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Stats.fxml"));
			getChildren().add(anchorPane);
		} catch (IOException e) {

			e.printStackTrace();
			WindowManager.getInstance().showMenu(WindowManager.MenuType.STARTUP_MENU);
		}
		exPick = new ExerciseComboBox(ExerciseReader.readAllExercises().asObservableList());
		getChildren().add(exPick);
	}
	
	
	
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize(){
		profileLabel = new Label();
		profile.showNameInJavaFXLabel(profileLabel);

		
		ArrayList<PieChart.Data> pieChartData = new ArrayList<PieChart.Data>();

		series1 = new Series<String, Integer>();
		series1.setName("totalError");
		
		for ( CodeStage stage : analyzedTrackingData.anMap.keySet()){
			AnalyzedStage data = analyzedTrackingData.anMap.get(stage);
			for (ErrorType type : ErrorType.values()){ // loop over error types
				//barChart.getData().add(	
						//new Data<String, Integer>(stage.toString(),data.error.getErrorCount(type))
				//		);
			}
			pieChartData.add(new PieChart.Data(stage.toString(),data.time));
		}
		xAxis.setLabel("Error");
		yAxis.setLabel("Number");
		barChart.getData().addAll(series1, series2, series3, series4, series5, series6, series7);
	
		pieChart.getData().addAll(pieChartData);
		pieChart.setTitle("Time");
		
    
	}
}