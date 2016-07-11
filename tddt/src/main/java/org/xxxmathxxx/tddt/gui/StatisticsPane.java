package org.xxxmathxxx.tddt.gui;
import java.io.IOException;

import org.xxxmathxxx.tddt.core.TDDT;
import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.profile.Profile;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedTrackingData;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedTrackingDataCollection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
   
	public StatisticsPane(){
		this.profile = TDDT.currentThread.getUserProfile();
				
		analyzedTrackingDataCollection = TDDT.currentThread.getUserProfile().profileStats.getAnalayzedTrackingData();	//WHY ARE WE PASSING AN ENTIRE COLLECTION HERE??? AND WHY IS THE METHOD NAME WRONG?
		
		try {
			AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Stats.fxml"));
			getChildren().add(anchorPane);
		} catch (IOException e) {

			e.printStackTrace();
			WindowManager.getInstance().showMenu(WindowManager.MenuType.STARTUP_MENU);
		}
		initialize();
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
		
		series1 = new Series<String, Integer>();
		series1.setName("totalError");
		series1.getData().add(new Data<String, Integer>("Stage Red", analyzedTrackingData.analyzedStageRed.error.totalError));
		series1.getData().add(new Data<String, Integer>("Stage Green", analyzedTrackingData.analyzedStageGreen.error.totalError));
		series1.getData().add(new Data<String, Integer>("Stage Refactor", analyzedTrackingData.analyzedStageGreen.error.totalError));
		
		series2 = new Series<String, Integer>();
		series2.setName("notInitializedError");
		series2.getData().add(new Data<String, Integer>("Stage Red", analyzedTrackingData.analyzedStageRed.error.notInitializedError));
		series2.getData().add(new Data<String, Integer>("Stage Green", analyzedTrackingData.analyzedStageGreen.error.notInitializedError));
		series2.getData().add(new Data<String, Integer>("Stage Refactor", analyzedTrackingData.analyzedStageRefactor.error.notInitializedError));
		
		series3 = new Series<String, Integer>();
		series3.setName("cannotFindError");
		series3.getData().add(new Data<String, Integer>("Stage Red", analyzedTrackingData.analyzedStageRed.error.cannotFindError));
		series3.getData().add(new Data<String, Integer>("Stage Green", analyzedTrackingData.analyzedStageGreen.error.cannotFindError));
		series3.getData().add(new Data<String, Integer>("Stage Refactor", analyzedTrackingData.analyzedStageRefactor.error.cannotFindError));
		
		series4 = new Series<String, Integer>();
		series4.setName("returnTypeError");
		series4.getData().add(new Data<String, Integer>("Stage Red", analyzedTrackingData.analyzedStageRed.error.returnTypeError));
		series4.getData().add(new Data<String, Integer>("Stage Green", analyzedTrackingData.analyzedStageGreen.error.returnTypeError));
		series4.getData().add(new Data<String, Integer>("Stage Refactor", analyzedTrackingData.analyzedStageRefactor.error.returnTypeError));
		
		series5 = new Series<String, Integer>();
		series5.setName("expectedError");
		series5.getData().add(new Data<String, Integer>("Stage Red", analyzedTrackingData.analyzedStageRed.error.expectedError));
		series5.getData().add(new Data<String, Integer>("Stage Green", analyzedTrackingData.analyzedStageGreen.error.expectedError));
		series5.getData().add(new Data<String, Integer>("Stage Refactor", analyzedTrackingData.analyzedStageRefactor.error.expectedError));
		
		series6 = new Series<String, Integer>();
		series6.setName("semnaticError");
		series6.getData().add(new Data<String, Integer>("Stage Red", analyzedTrackingData.analyzedStageRed.error.semanticError));
		series6.getData().add(new Data<String, Integer>("Stage Green", analyzedTrackingData.analyzedStageGreen.error.semanticError));
		series6.getData().add(new Data<String, Integer>("Stage Refactor", analyzedTrackingData.analyzedStageRefactor.error.semanticError));
		
		series7 = new Series<String, Integer>();
		series7.setName("syntaxError");
		series7.getData().add(new Data<String, Integer>("Stage Red", analyzedTrackingData.analyzedStageRed.error.syntaxError));
		series7.getData().add(new Data<String, Integer>("Stage Green", analyzedTrackingData.analyzedStageGreen.error.syntaxError));
		series7.getData().add(new Data<String, Integer>("Stage Refactor", analyzedTrackingData.analyzedStageRefactor.error.syntaxError));
		
		xAxis.setLabel("Error");
		yAxis.setLabel("Number");
		barChart.getData().addAll(series1, series2, series3, series4, series5, series6, series7);
	
		
		ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("StageRed", Integer.parseInt(analyzedTrackingData.analyzedStageRed.time)),
                new PieChart.Data("StageRefactor", Integer.parseInt(analyzedTrackingData.analyzedStageRefactor.time)),
                new PieChart.Data("StageGreen", Integer.parseInt(analyzedTrackingData.analyzedStageGreen.time)));
		
		pieChart.getData().addAll(pieChartData);
		pieChart.setTitle("Time");
		
    
	}
}