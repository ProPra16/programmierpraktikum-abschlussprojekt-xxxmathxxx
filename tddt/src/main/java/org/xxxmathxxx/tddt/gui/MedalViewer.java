package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.profile.MedalState;
import org.xxxmathxxx.tddt.profile.Profile;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**This Pane is able to show a graphical representation of a medalState
 * @author xxxMathxxx 2016
 *
 */
public class MedalViewer extends Pane{
	
	//as of now we are using a fixed resolution, thus sizes of this component are fixed through those statics
	
	/**
	 * The width in pixel
	 */
	private static int width = 128;
	/**
	 * The height in pixel
	 */
	private static int height = 64;
	
	/**
	 * The border size in pixel (top,bottom,left,right)
	 */
	private static int border = 4;
	
	/**
	 * The ImageView showing the bronze medal
	 */
	private ImageView bronze;
	/**
	 * The ImageView showing the silver medal
	 */
	private ImageView silver;
	/**
	 * The ImageView showing the gold medal
	 */
	private ImageView gold;
	/**
	 * The ImageView showing the author medal
	 */
	private ImageView author;
	
	
	/**Default constructor, defines layout and initializes medals.
	 * Note, that the author medal is hidden by default so people don't realize it's a feature.
	 */
	public MedalViewer(){
		
		//TODO: maybe improve the design and style
		
		this.setPrefSize(width, height);
		
		int medalSize = height-(2*border);
		
		this.bronze = new ImageView(GraphicsHelper.medalIconScaled(MedalState.BRONZE,medalSize ));
		this.silver = new ImageView(GraphicsHelper.medalIconScaled(MedalState.SILVER, medalSize));
		this.gold = new ImageView(GraphicsHelper.medalIconScaled(MedalState.GOLD,medalSize));
		this.author = new ImageView(GraphicsHelper.medalIconScaled(MedalState.AUTHOR,medalSize));
		
		this.bronze.relocate(border, border); 
		this.silver.relocate(border+medalSize+border, border);
		this.gold.relocate(border+medalSize+border+medalSize+border, border);
		this.author.relocate(border+medalSize+border+medalSize+border+medalSize+border, border);
		
		this.bronze.setFitWidth(medalSize);
		this.bronze.setFitHeight(medalSize);
		
		this.silver.setFitWidth(medalSize);
		this.silver.setFitHeight(medalSize);
		
		this.gold.setFitWidth(medalSize);
		this.gold.setFitHeight(medalSize);
		
		this.author.setFitWidth(medalSize);
		this.author.setFitHeight(medalSize);
		
		this.author.setOpacity(0);
		this.gold.setOpacity(0.2);
		this.silver.setOpacity(0.2);
		this.bronze.setOpacity(0.2);
		
		getChildren().addAll(bronze,silver,gold,author);
	}
	
	/**Shows the medal state for a given profile and exercise
	 * @param ex The Exercise
	 * @param pr The Profile
	 */
	public void setMedals(Exercise ex, Profile pr){
		
		//this is very stupid redundant code and
		//could be solved more elegant with 2^ but this is easy to maintain and understand
		
		boolean showBronze = false;
		boolean showSilver = false;
		boolean showGold = false;
		boolean showAuthor = false;

		if (pr.getMedalState(ex.id) == MedalState.AUTHOR){
			showAuthor = true;
			showBronze = true;
			showSilver = true;
			showGold = true;
		}
		else if (pr.getMedalState(ex.id) == MedalState.GOLD){
			showBronze = true;
			showSilver = true;
			showGold = true;
		}
		else if (pr.getMedalState(ex.id) == MedalState.SILVER){
			showBronze = true;
			showSilver = true;
		}
		else if (pr.getMedalState(ex.id) == MedalState.BRONZE){
			showBronze = true;
		}
		
		
		if (showBronze == true){
			bronze.setOpacity(1);
		}
		else{
			bronze.setOpacity(0.2);
		}
		
		if (showSilver == true){
			silver.setOpacity(1);
		}
		else{
			silver.setOpacity(0.2);
		}
		
		if (showGold == true){
			gold.setOpacity(1);
		}
		else{
			gold.setOpacity(0.2);
		}
		
		if (showAuthor == true){
			author.setOpacity(1);
		}
		else{
			author.setOpacity(0);
		}
	}

}
