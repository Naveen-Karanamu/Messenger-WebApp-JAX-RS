package rest_jax.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rest_jax.messenger.database.DatabaseClass;
import rest_jax.messenger.model.Profile;

public class ProfileService {
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("naveen", new Profile(1L, "naveen", "Naveen", "Kumar", null));
	}
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile> (profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() +1);
		profiles.put(profile.getFirstName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if(profile.getId() <=0) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
