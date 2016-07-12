package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.core.TDDT;
import org.xxxmathxxx.tddt.profile.MedalState;

import javafx.scene.image.Image;

/**Abstract class providing static general graphics functionality such as scaling images.
 * @author Philipp Spohr, Jun 29, 2016
 *
 */
public final class GraphicsHelper {
	
	private GraphicsHelper(){};
	
	/**Generates a medal image scaled to the given size (always a perfect square)
	 * @param medal The medal as MedalState
	 * @param pixelSize The size in pixel
	 * @return Returns the medal image as javafx Image
	 */
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
	
	public static Image defaultProfilePicture(double pixelSize){
		return new Image("file:graphics/unknownProfile.png",pixelSize,pixelSize,true,true);
	}
	
	public static String getResourcePath(String path){
		  return TDDT.class.getResource(path).toExternalForm();
	}
}
