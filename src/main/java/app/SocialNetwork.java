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
	// Must be the "Subject" in the Observer design pattern
	// Will notify the observers (panels) when something changes
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
	}

	/**
	 * Authenticates a username and password and logs a user in.
	 * @param username Entered username
	 * @param password Entered password
	 * @return boolean - whether a user logged in.
	 */
	public boolean auth(String username, char[] password){
		if (!profiles.containsKey(username) || username.length() < 3){
			return false;
		}

		String expectedPassword = username.substring(0, 3).toLowerCase() + profiles.get(username).getYear();
		char[] expectedChars = expectedPassword.toCharArray();
		if (Arrays.equals(password, expectedChars)){
			System.out.println("Logged in.");
			notifyObservers();
			return true;
		}
		System.out.println("Login failed.");
		return false;
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
	 * Calls for a post to be added to a Profile
	 * @param username name of the user writing
	 * @param message message body text
	 */
	public void addPost(String username, String message){
		profiles.get(username).addPost(message);
		notifyObservers();
	}

	/**
	 * Adds a connection to the given user, and notifies observers
	 * @param username profile to add the connection to
	 * @param friend user being added
	 */
	public void addConnection(String username, String friend){
		profiles.get(username).addConnection(friend);
		notifyObservers();
	}

	/**
	 * Removes a connection to the given user, and notifies observers
	 * @param username profile to add the connection to
	 * @param friend friend to be added
	 */
	public void removeConnection(String username, String friend){
		profiles.get(username).removeConnection(friend);
		notifyObservers();
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

	// FILL IN CODE: add variables, methods, and other classes

	// Also add JUnit tests for the project  - please see the pdf for details

}
