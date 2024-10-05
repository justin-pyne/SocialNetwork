package app;

import java.util.Arrays;

public class SocialNetworkController implements Controller{
    private SocialNetwork model;

    /**
     * Constructor for Controller that takes in the Model
     * @param model
     */
    public SocialNetworkController(SocialNetwork model) {
        this.model = model;
    }

    /**
     * Authenticates a username and password and logs a user in.
     * @param username Entered username
     * @param password Entered password
     * @return boolean - whether a user logged in.
     */
    @Override
    public boolean auth(String username, char[] password) {
        if (!model.containsProfile((username))|| username.length() < 3){
            return false;
        }

        String expectedPassword = username.substring(0, 3).toLowerCase() + model.getProfile(username).getYear();
        char[] expectedChars = expectedPassword.toCharArray();
        if (Arrays.equals(password, expectedChars)){
            System.out.println("Logged in.");
            model.notifyObservers();
            return true;
        }
        System.out.println("Login failed.");
        return false;
    }

    /**
     * Calls for a post to be added to a Profile
     * @param username name of the user writing
     * @param message message body text
     */
    @Override
    public void addPost(String username, String message) {
        model.getProfile(username).addPost(message);
        model.notifyObservers();
    }

    /**
     * Adds a connection to the given user, and notifies observers
     * @param username profile to add the connection to
     * @param friend user being added
     */
    @Override
    public void addFriend(String username, String friend) {
        model.getProfile(username).addConnection(friend);
        model.notifyObservers();
    }

    /**
     * Removes a connection to the given user, and notifies observers
     * @param username profile to add the connection to
     * @param friend friend to be added
     */
    @Override
    public void removeFriend(String username, String friend) {
        model.getProfile(username).removeConnection(friend);
        model.notifyObservers();
    }

    @Override
    public void addSupporter(String user, String org) {
        if (model.getProfile(user) instanceof UserProfile && model.getProfile(org) instanceof OrganizationProfile) {
            model.getProfile(org).addConnection(user);
            ((UserProfile) (model.getProfile(user))).startFollowing(org);
            model.notifyObservers();
        }
    }

    @Override
    public void removeSupporter(String user, String org) {
        if (model.getProfile(user) instanceof UserProfile && model.getProfile(org) instanceof OrganizationProfile) {
            model.getProfile(org).removeConnection(user);
            ((UserProfile) (model.getProfile(user))).stopFollowing(org);
            model.notifyObservers();
        }
    }
}
