package org.xxxmathxxx.tddt.gui.scenes;
import java.io.IOException;

import org.xxxmathxxx.tddt.core.TDDT;
import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;
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
public class StatsController extends Pane{ //suggestion: move this to gui package and add only the scene that contains this to gui.scenes
	
	private Pane pane ;
	private Profile profile;
	private AnalyzedTrackingDataCollection analyzedTrackingDataCollection;
	AnalyzedTrackingData analyzedTrackingData;
	@SuppressWarnings("rawtypes")
	private  XYChart.Series series1;
	@SuppressWarnings("rawtypes")
	private  XYChart.Series series2;
	@SuppressWarnings("rawtypes")
	private  XYChart.Series series3;
	@SuppressWarnings("rawtypes")
	private  XYChart.Series series4;
	@SuppressWarnings("rawtypes")
	private  XYChart.Series series5;
	@SuppressWarnings("rawtypes")
	private  XYChart.Series series6;
	@SuppressWarnings("rawtypes")
	private  XYChart.Series series7;
	@FXML
	private BarChart<Number, String> barChart;
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
    
   
	public StatsController(Pane mainPane){
		
		this.profile = TDDT.currentThread.getUserProfile();
		
		this.pane = mainPane;
		this.analyzedTrackingData = TDDT.currentThread.getAnalyzedTrackingData();	
		
		analyzedTrackingDataCollection = profile.profileStats.getAnalayzedTrackingData();
		
		if(analyzedTrackingData == null){
			analyzedTrackingData = (AnalyzedTrackingData) analyzedTrackingDataCollection.get(0);
			if(analyzedTrackingData == null){
				TDDTLogManager.getInstance().logMessage("Cant find any AnalyzedTrackingData in profile: " + profile.getName());
			}
		}
	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Stats.fxml"));
		loader.setController(this);
		
		try {
			AnchorPane anchorPane = loader.load();
			pane.getChildren().add(anchorPane);
			this.getChildren().add(pane);
		} catch (IOException e) {

			e.printStackTrace();
		}
	
	}
	
	
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize(){
		
		profileLabel.setText(profile.getName());
		MenuItem[] menuItem = new MenuItem[analyzedTrackingDataCollection.size()];
		
		
		for(int i = 0; i < analyzedTrackingDataCollection.size(); i++){
			AnalyzedTrackingData data = (AnalyzedTrackingData) analyzedTrackingDataCollection.get(i);
			menuItem[i] = new MenuItem(data.exercise);
			menuButton.getItems().add(menuItem[i]);
			int i2 = i;
			
			menuItem[i].setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					if(event.getSource() == menuItem[i2]){
						
						TDDT.currentThread.setAnalyzedTrackingData((AnalyzedTrackingData) analyzedTrackingDataCollection.get(i2));
						WindowManager.getInstance().showMenu(WindowManager.MenuType.STATISTICS);
					}		
				}
			});
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