package app;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.*;

/*
 * File: SocialNetwork.java
 * -------------------------------
 * This class keeps track of the profiles.
 */
public class SocialNetwork implements Subject{
	private TreeMap<String, Profile> profiles = new TreeMap<>();
	private List<Observer> observers = new ArrayList<>();

	/**
	 * Empty constructor for the SocialNetwork
	 */
	public SocialNetwork() {
	}

	/**
	 * Loads and parses a Json to initialize the profiles TreeMap.
	 * @param filePath filePath of profiles Json
	 */
	public void loadJson(String filePath, ProfileFactoryInterface factory){
		JsonProcessor jp = new JsonProcessor();
		List<Profile> profilesList = jp.parseProfiles(filePath, factory);
		for (Profile profile : profilesList){
			profiles.put(profile.getName(), profile);
		}
		for (String profile : profiles.keySet()){
			if (profiles.get(profile) instanceof OrganizationProfile){
				for (String supporter : ((OrganizationProfile) profiles.get(profile)).getSupporters()){
					((UserProfile)profiles.get(supporter)).startFollowing(profile);
				}
			}
		}
	}

	/**
	 * Returns the requested Profile from the profiles map
	 * @param name name of profile
	 * @return Profile object
	 */
	public Profile getProfile(String name){
		return profiles.get(name);
	}

	/**
	 * Checks if a profile is in the Social Network
	 * @param name name of profile
	 * @return boolean of if the profile exists
	 */
	public boolean containsProfile(String name){
		return profiles.containsKey(name);
	}

	/**
	 * serializes this Social Network into a JSONArray
	 * @return JsonArray of the social network
	 */
	public JsonArray serializeSocialNetwork(){
		JsonArray jsonArr = new JsonArray();
		for (String profile : profiles.keySet()){
			JsonObject obj = profiles.get(profile).serializeProfile();
			jsonArr.add(obj);
		}
		return jsonArr;
	}


	/**
	 * Registers an observer to the Observer list of this Social Network
	 * @param o Observer to be registered
	 */
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	/**
	 * Removes an observer from the Observer list of this Social Network.
	 * @param o Observer to be removed
	 */
	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	/**
	 * Updates all observers of this Social Network to changes.
	 */
	@Override
	public void notifyObservers() {
		for (Observer o : observers){
			o.update();
		}

	}

	/**
	 * Returns the first user in the profiles map
	 * @return Name of first profile
	 */
	public String getDefaultProfile() {
		for (String user : profiles.keySet()){
			if (getProfile(user).getType().equals("user")){
				return user;
			}
		}
		return null;
	}
}
