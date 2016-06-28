package org.xxxmathxxx.tddt.profile;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import org.xxxmathxxx.tddt.errors.TDDTIOError;
import org.xxxmathxxx.tddt.profile.Profile;

/*Author: Tschebyscheff, 22.06.16
 * A class that contains a list of Profles
 */

/**
 * @author Philipp Spohr, Jun 28, 2016
 *
 */
public class ProfileTests {

	@Test
	public void genericIOTest(){
		try {
			File testFolder = new File("tests");
			testFolder.mkdirs();
			Profile profile = new Profile("Fourier","graphics/faces/fou.png");
			profile.saveProfileToFile("tests/fourier.save");
			Profile profile2 = Profile.loadProfileFromFile("tests/fourier.save");
			assertEquals(profile.toString(),profile2.toString());
			
			//Cleanup
			new File("tests/fourier.save").delete();
			testFolder.delete();
		} catch (TDDTIOError e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void advancedIOTest(){
		try {
			File testFolder = new File("tests");
			testFolder.mkdirs();
			Profile profile = new Profile("Fourier","graphics/faces/fou.png");
			profile.setMedalState(999, MedalState.BRONZE);
			profile.saveProfileToFile("tests/fourier.save");
			Profile profile2 = Profile.loadProfileFromFile("tests/fourier.save");
			assertEquals(profile2.getMedalState(999),MedalState.BRONZE);
			//Cleanup
			new File("tests/fourier.save").delete();
			testFolder.delete();
		} catch (TDDTIOError e) {
			e.printStackTrace();
		}
	}	
}
