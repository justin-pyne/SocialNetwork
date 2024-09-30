package app;

import java.util.*;

/*
 * File: SocialNetwork.java
 * -------------------------------
 * This class keeps track of the profiles.
 */
public class SocialNetwork implements Subject{
	// Must be the "Subject" in the Observer design pattern
	// Will notify the observers (panels) when something changes
	private Map<String, Profile> profiles = new HashMap<>();
	private List<Observer> observers = new ArrayList<>();

	public SocialNetwork() {
	}

	public void loadJson(String filePath){
		JsonProcessor jp = new JsonProcessor();
		profiles = jp.parseProfiles(filePath);
	}

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

	public Map<String, Profile> getProfiles() {
		return Map.copyOf(profiles);
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers){
			o.update();
		}

	}

	// FILL IN CODE: add variables, methods, and other classes

	// Also add JUnit tests for the project  - please see the pdf for details

}
