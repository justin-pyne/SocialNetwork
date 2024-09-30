package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * File: SocialNetwork.java
 * -------------------------------
 * This class keeps track of the profiles.
 */
public class SocialNetwork implements Subject{
	// Must be the "Subject" in the Observer design pattern
	// Will notify the observers (panels) when something changes
	public Map<String, Profile> profiles = new HashMap<>();

	public SocialNetwork() {
	}

	public void loadJson(String filePath){
		JsonProcessor jp = new JsonProcessor();
		profiles = jp.parseProfiles(filePath);
	}

	public boolean auth(){
		return false;
	}



	@Override
	public void registerObserver(Observer o) {

	}

	@Override
	public void removeObserver(Observer o) {

	}

	@Override
	public void notifyObservers() {

	}

	// FILL IN CODE: add variables, methods, and other classes

	// Also add JUnit tests for the project  - please see the pdf for details

}
