package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.core.TDDT;
import org.xxxmathxxx.tddt.profile.MedalState;

import javafx.scene.image.Image;

/**
 * Abstract class providing static general graphics functionality such as scaling images.
 * @author Philipp Spohr, Jun 29, 2016
 */
public final class GraphicsHelper {
	
	/**
	 * Generates a medal image scaled to the given size (always a perfect square)
	 * @param medal The medal as MedalState
	 * @param pixelSize The size in pixel
	 * @return Returns the medal image as javafx Image
	 */
	public static Image medalIconScaled(MedalState medal, double pixelSize){
		if (medal == MedalState.BRONZE){
			return new Image(getResourcePath("/graphics/medals/bronze.png"),pixelSize,pixelSize,true,true);
		}
		else if (medal == MedalState.SILVER){
			return new Image(getResourcePath("/graphics/medals/silver.png"),pixelSize,pixelSize,true,true);
		}
		else if (medal == MedalState.GOLD){
			return new Image(getResourcePath("/graphics/medals/gold.png"),pixelSize,pixelSize,true,true);
		}
		else if (medal == MedalState.AUTHOR){
			return new Image(getResourcePath("/graphics/medals/author.png"),pixelSize,pixelSize,true,true);
		}
		return null;
	}
	
	/**
	 * Returns default profile pic
	 * @param pixelSize preffered size
	 * @return Scaled version of the default pic
	 */
	public static Image defaultProfilePicture(double pixelSize){
		return new Image(getResourcePath("/graphics/unknownProfile.png"),pixelSize,pixelSize,true,true);
	}
	
	/**
	 * Returns ressource path
	 * @param path Path to ressources
	 * @return ressource path
	 */
	public static String getResourcePath(String path){
		  return TDDT.class.getResource(path).toExternalForm();
	}
}
