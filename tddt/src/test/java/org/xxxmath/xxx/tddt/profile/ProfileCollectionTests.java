package org.xxxmath.xxx.tddt.profile;

import static org.junit.Assert.*;

import org.junit.Test;
import org.xxxmathxxx.tddt.profile.Profile;
import org.xxxmathxxx.tddt.profile.ProfileCollection;

/*Author: Tschebyscheff, 22.06.16
 * A class that contains a list of Profles
 */

public class ProfileCollectionTests {

	@Test
	public void addProfileTest(){
		Profile profile = new Profile("Fabian");
		Profile profile2 = new Profile("Phillip");
		Profile profile3 = new Profile("Chris");
		ProfileCollection profCollection = new ProfileCollection();
		
		profCollection.addProfile(profile);
		profCollection.addProfile(profile2);
		profCollection.addProfile(profile3);
		
		assertEquals(profile2, profCollection.getProfile(1));
	}
	@Test
	public void deleteProfileTest(){
		Profile profile = new Profile("Fabian");
		Profile profile2 = new Profile("Phillip");
		Profile profile3 = new Profile("Chris");
		ProfileCollection profCollection = new ProfileCollection();
		
		profCollection.addProfile(profile);
		profCollection.addProfile(profile2);
		profCollection.addProfile(profile3);
		profCollection.deleteProfile(1);
		
		assertEquals(profile3, profCollection.getProfile(1));
	}
	
}
