package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.profile.MedalState;

import javafx.scene.image.Image;

public final class GraphicsHelper {
	
	private GraphicsHelper(){};
	
	public static Image medalIconScaled(MedalState medal, double pixelSize){
		if (medal == MedalState.BRONZE){
			return new Image("file:graphics/medals/bronze.png",pixelSize,pixelSize,true,true);
		}
		else if (medal == MedalState.SILVER){
			return new Image("file:graphics/medals/silver.png",pixelSize,pixelSize,true,true);
		}
		else if (medal == MedalState.GOLD){
			return new Image("file:graphics/medals/gold.png",pixelSize,pixelSize,true,true);
		}
		else if (medal == MedalState.AUTHOR){
			return new Image("file:graphics/medals/author.png",pixelSize,pixelSize,true,true);
		}
		return null;
	}
	
}
