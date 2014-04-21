package general;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Sonia Rode
 * 
 * Contains all the Profiles.
 *
 */
public class ProfileManager {
	
	/**
	 * Factory method to return a new Profile.
	 */
	public static Profile createEmptyProfile(){
		return new Profile("");
	}
	
	private Map<String, Profile> profiles;
	
	/**
	 * Create a ProfileManager initially containing the default profile.
	 */
	public ProfileManager(){
		profiles = new HashMap<String, Profile>();
		
		// Add the default Profile
		profiles.put("default", new Profile("default"));
	}
	
	/**
	 * Create a ProfileManager initially containing the given collection of
	 * Profiles.
	 * 
	 * @param profiles initial collection of Profiles
	 */
	public ProfileManager(Collection<Profile> profiles){
		for (Profile profile : profiles){
			this.profiles.put(profile.getName(), profile);
		}
	}
	
	/**
	 * Returns the default Profile.
	 */
	public Profile getDefaultProfile(){
		return profiles.get("default");
	}
	
	/**
	 * Adds a Profile to this ProfileManager.
	 * 
	 * @param profile the Profile to add
	 */
	public void addProfile(Profile profile){
		profiles.put(profile.getName(), profile);
	}
	
	/**
	 * Returns all the Profiles managed by this ProfileManager.
	 */
	public Collection<Profile> allProfiles(){
		return profiles.values();
	}
	
	/**
	 * Returns the names of all the Profiles managed by this ProfileManager.
	 */
	public Collection<String> allProfileNames(){
		return profiles.keySet();
	}
	
	/**
	 * Returns the Profile with the given name. Throws an exception if there
	 * is no such Profile.
	 * 
	 * @param name the name of the Profile to return
	 */
	public Profile getProfileWithName(String name){
		if (profiles.get(name) == null)
			throw new RuntimeException("Profile with name "+name+" doesn't exist.");
		return profiles.get(name);
	}
}
