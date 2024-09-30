package app;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

abstract public class Profile {
    private String name;
    private int year;
    private String image;
    private List<Post> posts;
    private List<Profile> connections = new ArrayList<>();

    public Profile(JsonObject obj) {
        this.name = obj.get("name").getAsString();
        this.year = obj.get("year").getAsInt();
        this.image = obj.get("image").getAsString();
        this.posts =
        this.connections = ;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getImage() {
        return image;
    }

    public List<Profile> getConnections(){
        return List.copyOf(connections);
    };

    public void addPost(Post post){
        posts.add(post);
        // notify observers of profile change, write to json
    }

    public abstract String getType();
    public abstract void addConnection(String name);
    public abstract void removeConnection(String name);
}
