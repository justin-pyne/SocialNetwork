package app;

import com.google.gson.JsonObject;

import java.util.List;

public class UserProfile extends Profile{


    public UserProfile(JsonObject obj) {
        super(obj);
    }

    @Override
    public String getType() {
        return "user";
    }

    @Override
    public void addConnection(String name) {

    }

    @Override
    public void removeConnection(String name) {

    }
}
