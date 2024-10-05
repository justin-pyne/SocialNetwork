package app;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;


import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a User's profile on the Social Network.
 */
public class UserProfile extends Profile{

    private List<String> friends = new ArrayList<>();
    private List<String> followedOrgs = new ArrayList<>();
    /**
     * Constructor to create a UserProfile
     * @param name name of user
     * @param year year of user
     * @param image image filename
     * @param posts list of posts by user
     * @param friends list of friends of user
     */
    public UserProfile(String name, int year, String image, List<Post> posts, List<String> friends) {
        super(name, year, image, posts);
        this.friends = friends;
    }

    /**
     * Getter for friends list
     * @return A copy of the friends list represented by Strings
     */
    public List<String> getFriends() {
        return List.copyOf(friends);
    }

    /**
     * Tells the type of profile for this object
     * @return A string representing the type of profile
     */
    @Override
    public String getType() {
        return "user";
    }

    /**
     * Adds a friend to the user's friends list.
     * @param name Name of the connection to add
     */
    @Override
    public void addConnection(String name) {
        if (!friends.contains(name)){
            friends.add(name);
        }
    }

    /**
     * Removes a friend from a user's friends list.
     * @param name Name of the connection to remove.
     */
    @Override
    public void removeConnection(String name) {
        friends.remove(name);
    }

    /**
     * Stops following the given organization
     * @param org Organization to unfollow
     */
    public void stopFollowing(String org) {
        followedOrgs.remove(org);
    }

    /**
     * Starts following the given organization
     * @param org Organization to follow
     */
    public void startFollowing(String org) {
        if (!followedOrgs.contains(org)){
            followedOrgs.add(org);
        }
    }

    /**
     * Getter for list of followed organizations
     * @return A copy of the followed organization list
     */
    public List<String> getFollowedOrgs(){
        return List.copyOf(followedOrgs);
    }

    /**
     * Serializes this profile into a Json Object.
     * @return JsonObject representing this profile.
     */
    @Override
    public JsonObject serializeProfile() {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "user");
        obj.addProperty("name", this.getName());
        obj.addProperty("year", this.getYear());
        obj.addProperty("image", this.getImage());
        obj.add("posts", serializePostList());
        obj.add("friends", serializeFriends());
        return obj;
    }


    /**
     * Private helper to serialize friends list into a JsonArray.
     * @return
     */
    private JsonArray serializeFriends(){
        JsonArray JsonArr = new JsonArray();
        for (String friend : this.getFriends()){
            JsonArr.add(friend);
        }
        return JsonArr;
    }


    /**
     * toString method for this Object
     * @return A string representation of this object
     */
    @Override
    public String toString() {
        return "UserProfile{" + super.toString() +
                '\'' + '\n' + "friends=" + friends +
                '}';
    }
}
