package org.xxxmathxxx.tddt.gui;
import java.io.IOException;
import java.util.ArrayList;

import org.xxxmathxxx.tddt.core.TDDTThread;
import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.io.ExerciseReader;
import org.xxxmathxxx.tddt.profile.Profile;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedStage;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedTrackingData;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedTrackingDataCollection;
import org.xxxmathxxx.tddt.tracking_analysis.ErrorType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Tschebyscheff
 * TODO: handle MenuButton(ActionEvent) and backButton
 *
 */
public class StatisticsPane extends Pane{ //suggestion: move this to gui package and add only the scene that contains this to gui.scenes

	private Profile profile;
	
	private AnalyzedTrackingDataCollection analyzedTrackingDataCollection;
	
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
				
		analyzedTrackingDataCollection = TDDTThread.getInstance().getUserProfile().profileStats.getAnalayzedTrackingData();	//WHY ARE WE PASSING AN ENTIRE COLLECTION HERE??? AND WHY IS THE METHOD NAME WRONG?
		
		try {
			AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Stats.fxml"));
			getChildren().add(anchorPane);
		} catch (IOException e) {

			e.printStackTrace();
			WindowManager.getInstance().showMenu(WindowManager.MenuType.STARTUP_MENU);
		}
		exPick = new ExerciseComboBox(ExerciseReader.readAllExercises().asObservableList());
	}
	
	
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize(){
		profileLabel = new Label();
		profile.showNameInJavaFXLabel(profileLabel);
		MenuItem[] menuItem = new MenuItem[analyzedTrackingDataCollection.size()];
		
		int counter = 0;
		
		for(Exercise ex : analyzedTrackingDataCollection.keySet()){
			//AnalyzedTrackingData data = (AnalyzedTrackingData) analyzedTrackingDataCollection.get(ex); AS OF NOW NOT EVEN USED
			menuItem[counter] = new MenuItem(ex.name);
			menuButton.getItems().add(menuItem[counter]);
			
			menuItem[counter].setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					if(event.getSource() == menuButton){
						WindowManager.getInstance().showMenu(WindowManager.MenuType.EXISTING_PROFILE); //?
					}		
				}
			});
			counter++;
		}
		
		ArrayList<PieChart.Data> pieChartData = new ArrayList<PieChart.Data>();

		series1 = new Series<String, Integer>();
		series1.setName("totalError");
		for (AnalyzedStage as : analyzedTrackingData.anMap.values()){
			series1.getData().add(new Data<String, Integer>("Stage Red",as.error.getErrorCount(ErrorType.ANY)));
			pieChartData.add(new PieChart.Data("StageRed", as.time));
		}
		xAxis.setLabel("Error");
		yAxis.setLabel("Number");
		barChart.getData().addAll(series1, series2, series3, series4, series5, series6, series7);
	
		pieChart.getData().addAll(pieChartData);
		pieChart.setTitle("Time");
		
    
	}
}