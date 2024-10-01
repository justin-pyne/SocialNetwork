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
        if (!friends.contains(name)){
            friends.add(name);
        }
    }

    @Override
    public void removeConnection(String name) {
        friends.remove(name);
    }

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


    public JsonArray serializeFriends(){
        JsonArray JsonArr = new JsonArray();
        for (String friend : this.getFriends()){
            JsonArr.add(friend);
        }
        return JsonArr;
    }

    @Override
    public String toString() {
        return "UserProfile{" + super.toString() +
                '\'' + '\n' + "friends=" + friends +
                '}';
    }
}
