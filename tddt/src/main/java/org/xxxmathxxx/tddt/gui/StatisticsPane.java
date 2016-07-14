package org.xxxmathxxx.tddt.gui;
import java.io.IOException;

import org.xxxmathxxx.tddt.core.TDDTThread;
import org.xxxmathxxx.tddt.data.CodeStage;
import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.io.ExerciseReader;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.Profile;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedStage;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedTrackingData;
import org.xxxmathxxx.tddt.tracking_analysis.ErrorType;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * @author xxxMath2016xxx
 *
 */
public class StatisticsPane extends Pane{  

	private Profile profile;
	private AnalyzedTrackingData analyzedTrackingData; 

	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private Label profileLabel;
	@FXML
	private Label totalTimeLabel;
	@FXML
	private Label totalKeystrokesLabel;
	@FXML
	private PieChart pieChart;
	@FXML
	private PieChart pieChartKeystrokes;
	@FXML
	private HBox topBar;
	@FXML
	private ImageView profileImageView;
	
	//non fxml elements
	private ExerciseComboBox exercisePicker;
	private MedalViewer medalViewer;
	private Button back;
    
	/**
	 * Pane to show stats
	 */
	public StatisticsPane(){
		
		this.profile = TDDTThread.getInstance().getUserProfile();
		profileLabel = new Label();
		profile.showNameInJavaFXLabel(profileLabel);
		
		
		//Never ever delete some FXML stuff, it behaves different in combination with gradle
		java.net.URL location = getClass().getResource("/Stats.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(location);
		fxmlLoader.setController(this);
		
		try {
			//Add non-fxml elements
			AnchorPane anchorPane = fxmlLoader.load(location.openStream());
			
			exercisePicker = new ExerciseComboBox(ExerciseReader.readAllExercises().asObservableList());
			exercisePicker.getSelectionModel().selectedItemProperty().addListener(changeListener);
			exercisePicker.setPrefSize(196, 32);
			exercisePicker.relocate(16, 16);
			anchorPane.getChildren().add(exercisePicker);
			
			medalViewer = new MedalViewer();
			topBar.getChildren().add(medalViewer);
			
			back = new Button("Back");
			back.setPrefSize(150, 25);
			back.relocate(800-475,600-90);
			back.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
			anchorPane.getChildren().add(back);
			
			//Add fxml pane
			getChildren().add(anchorPane);
			
		} catch (IOException e) {
			e.printStackTrace();
			WindowManager.getInstance().showMenu(WindowManager.MenuType.STARTUP_MENU);
		}
	}
	
	@FXML
	private void initialize(){
		
		Profile profile = TDDTThread.getInstance().getUserProfile();
		
		profile.showImageInJavaFXImageView(profileImageView);
		profile.showNameInJavaFXLabel(profileLabel);
		
		if(analyzedTrackingData != null){
			
			AnalyzedStage stage1 = analyzedTrackingData.anMap.get(CodeStage.TEST);
			AnalyzedStage stage2 = analyzedTrackingData.anMap.get(CodeStage.CODE);
			AnalyzedStage stage3 = analyzedTrackingData.anMap.get(CodeStage.REFACTOR);

			int totalTime = (int)(stage1.time+stage2.time+stage3.time);
			totalTimeLabel.setText("Total time elapsed: "+totalTime);
			
			pieChart.getData().clear();
			ObservableList<PieChart.Data> pieChartData =
	                FXCollections.observableArrayList(
	                new PieChart.Data("StageRed", (int) stage1.time ),
	                new PieChart.Data("StageRefactor", (int) stage3.time ),
	                new PieChart.Data("StageGreen", (int) stage2.time ));
			
			pieChart.getData().addAll(pieChartData);
			pieChart.setTitle("Time");
			
			int totalKeystrokes = stage1.getKeystrokes()+stage2.getKeystrokes()+stage3.getKeystrokes();
			totalKeystrokesLabel.setText("Total keystrokes: "+totalKeystrokes);
			
			pieChartKeystrokes.getData().clear();
			pieChartData =
	                FXCollections.observableArrayList(
	                new PieChart.Data("StageRed", (int) stage1.getKeystrokes() ),
	                new PieChart.Data("StageRefactor", (int) stage3.getKeystrokes() ),
	                new PieChart.Data("StageGreen", (int) stage2.getKeystrokes() ));
			
			pieChartKeystrokes.getData().addAll(pieChartData);
			pieChartKeystrokes.setTitle("Keystrokes");
			
			barChart.getData().clear();
			for (ErrorType e : ErrorType.values()){
				XYChart.Series<String, Integer> curSeries = new Series<String, Integer>();
				curSeries.setName(e.toString());
				curSeries.getData().add(new Data<String, Integer>("Stage Red (Test)", stage1.error.getErrorCount(e)));
				curSeries.getData().add(new Data<String, Integer>("Stage Green (Code)", stage2.error.getErrorCount(e)));
				curSeries.getData().add(new Data<String, Integer>("Stage Refactor", stage3.error.getErrorCount(e)));
				barChart.getData().add(curSeries);
			}
			barChart.setCategoryGap(24);
			barChart.setAnimated(false);//Retarded JavaFX, so after being desperate about this I finally found out that this is a known bug in JavaFX and disabling the animation is the only fix
			xAxis.setLabel("ErrorType");
			yAxis.setLabel("Number");
		}
	}

	ChangeListener<Exercise> changeListener = new ChangeListener<Exercise>(){

		@Override
		public void changed(ObservableValue<? extends Exercise> observable, Exercise oldValue, Exercise newValue) {
			if (newValue != null) {
				analyzedTrackingData = profile.profileStats.getTrackingData(newValue.id);
				if (analyzedTrackingData == null){
					AlertMessenger.showErrorMessage("No data recorded!", "We couldn't find any data for this exercise. Maybe you haven't completed it yet?");
				}
				
				medalViewer.setMedals(newValue, TDDTThread.getInstance().getUserProfile());
				initialize();
			}
		}
	};
	
	
	/**
	 * Handler for the different buttons
	 */
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == back){
				WindowManager.getInstance().showMenu(WindowManager.MenuType.EXISTING_PROFILE);
			}
		}
	}
	
}