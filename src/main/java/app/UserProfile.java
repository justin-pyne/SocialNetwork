package app;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;


import java.util.ArrayList;
import java.util.List;

public class UserProfile extends Profile{

    private List<String> friends = new ArrayList<>();

    public UserProfile(JsonObject obj) {
        super(obj);

        JsonArray friendsArray = obj.getAsJsonArray("friends");
        for (JsonElement friendEle : friendsArray){
            String name = friendEle.getAsString();
            friends.add(name);
        }
    }

    public List<String> getFriends() {
        return List.copyOf(friends);
    }

    @Override
    public String getType() {
        return "user";
    }

    @Override
    public void addConnection(String name) {
        friends.add(name);
    }

    @Override
    public void removeConnection(String name) {
        friends.remove(name);
    }

    @Override
    public String toString() {
        return "UserProfile{" + super.toString() +
                '\'' + '\n' + "friends=" + friends +
                '}';
    }
}
