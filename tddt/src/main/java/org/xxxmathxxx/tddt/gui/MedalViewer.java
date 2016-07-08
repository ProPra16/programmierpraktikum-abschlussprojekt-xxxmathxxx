package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.profile.MedalState;
import org.xxxmathxxx.tddt.profile.Profile;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MedalViewer extends Pane{
	
	private static int width = 128;
	private static int height = 64;
	
	private static int border = 4;
	
	private ImageView bronze;
	private ImageView silver;
	private ImageView gold;
	private ImageView author;
	
	
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
		
		getChildren().addAll(bronze,silver,gold,author);
	}
	
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
