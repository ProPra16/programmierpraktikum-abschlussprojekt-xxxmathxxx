package org.xxxmathxxx.tddt.gui;
import java.io.IOException;
import java.util.ArrayList;

import org.xxxmathxxx.tddt.core.TDDTThread;
import org.xxxmathxxx.tddt.data.CodeStage;
import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.gui.WindowManager.MenuType;
import org.xxxmathxxx.tddt.io.ExerciseReader;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.Profile;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedStage;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedTrackingData;
import org.xxxmathxxx.tddt.tracking_analysis.ErrorType;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Tschebyscheff
 *
 */
public class StatisticsPane extends Pane{  

	private Profile profile;
	private AnalyzedTrackingData analyzedTrackingData; 
	private  ArrayList<  XYChart.Series<String,Integer> > seriesList; 
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
	@FXML
	private Button buttonBack;
	@FXML
	private ExerciseComboBox exPick;
    
	
   
	public StatisticsPane(){
		
		this.profile = TDDTThread.getInstance().getUserProfile();
		profileLabel = new Label();
		profile.showNameInJavaFXLabel(profileLabel);
		
		//Never ever delete some FXML stuff, it behaves different in combination with gradle
		java.net.URL location = getClass().getResource("Stats.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(location);
		fxmlLoader.setController(this);
		
		
		try {
			//Never ever delete some FXML stuff, it behaves different in combination with gradle
			AnchorPane anchorPane = fxmlLoader.load(location.openStream());
			exPick = new ExerciseComboBox(ExerciseReader.readAllExercises().asObservableList());
			exPick.getSelectionModel().selectedItemProperty().addListener(changeListener);
			anchorPane.getChildren().add(exPick);
			getChildren().add(anchorPane);
			
		} catch (IOException e) {
			e.printStackTrace();
			WindowManager.getInstance().showMenu(WindowManager.MenuType.STARTUP_MENU);
		}
	}
	
	
	
	@FXML
	private void initialize(){

		
		ArrayList<PieChart.Data> pieChartData = new ArrayList<PieChart.Data>();

		if(analyzedTrackingData != null){
			for ( CodeStage stage : analyzedTrackingData.anMap.keySet()){
				AnalyzedStage data = analyzedTrackingData.anMap.get(stage);
				for (ErrorType type : ErrorType.values()){ // loop over error types
					Series<String,Integer> tmpSeries = new Series<String, Integer>();
					tmpSeries.getData().add(new Data<String, Integer>(stage.toString(),data.error.getErrorCount(type)));
					seriesList.add(tmpSeries);
				}
				pieChartData.add(new PieChart.Data(stage.toString(),data.time));
			}
			
			xAxis.setLabel("Error");
			yAxis.setLabel("Number");
			barChart.getData().addAll(seriesList);
	
			pieChart.getData().addAll(pieChartData);
			pieChart.setTitle("Time");
		}
		
		
	}

	ChangeListener<Exercise> changeListener = new ChangeListener<Exercise>(){

		@Override
		public void changed(ObservableValue<? extends Exercise> observable, Exercise oldValue, Exercise newValue) {
			if (newValue != null) {
				try{
				analyzedTrackingData = profile.profileStats.getTrackingData(newValue.id);
				initialize();
				}
				catch(NullPointerException e){
					TDDTLogManager.getInstance().logMessage("No Statistics available for " + newValue.name);
				}
			}
		}
	};
	
	@FXML
	public void handleButtonBack(){
		WindowManager.getInstance().showMenu(MenuType.EXISTING_PROFILE);
	}
	
}