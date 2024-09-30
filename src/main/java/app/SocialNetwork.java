package app;

import java.util.ArrayList;
import java.util.List;

/*
 * File: SocialNetwork.java
 * -------------------------------
 * This class keeps track of the profiles.
 */
public class SocialNetwork implements Subject{
	// Must be the "Subject" in the Observer design pattern
	// Will notify the observers (panels) when something changes
	public List<Profile> profiles = new ArrayList<Profile>();

	public SocialNetwork() {
	}

	public void loadJson(String filePath){

	}

	public boolean auth(){

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
