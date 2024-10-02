package app;

import com.sun.source.tree.Tree;

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
	public void loadJson(String filePath){
		JsonProcessor jp = new JsonProcessor();
		profiles = jp.parseProfiles(filePath);
	}

	/**
	 * Authenticates a username and password and logs a user in.
	 * @param username Entered username
	 * @param password Entered password
	 * @return boolean - whether a user logged in.
	 */
	public boolean auth(String username, char[] password){
		if (!profiles.containsKey(username) || !(profiles.get(username) instanceof UserProfile) || username.length() < 3){
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
	 * Gets the Profile map
	 * @return A TreeMap of the profiles on the SocialNetwork
	 */
	public TreeMap<String, Profile> getProfiles() {
		return profiles;
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
