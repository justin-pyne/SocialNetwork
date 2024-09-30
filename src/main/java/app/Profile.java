package app;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

abstract public class Profile {
    private String name;
    private int year;
    private String image;
    private List<Post> posts;

    public Profile(JsonObject obj) {
        this.name = obj.get("name").getAsString();
        this.year = obj.get("year").getAsInt();
        this.image = obj.get("image").getAsString();
        this.posts = parsePosts(obj);
    }

    private List<Post> parsePosts(JsonObject obj){
        List<Post> posts = new ArrayList<>();
        JsonArray postsArray = obj.getAsJsonArray("posts");
        for (JsonElement ele : postsArray){
            JsonObject postObj = (JsonObject)ele;
            String message = postObj.get("message").getAsString();
            String timestamp = postObj.get("timestamp").getAsString();
            Post post = new Post(message, timestamp);
            posts.add(post);
        }
        return posts;
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

    public void addPost(Post post){
        posts.add(post);
        // notify observers of profile change, write to json
    }

    public abstract String getType();
    public abstract void addConnection(String name);
    public abstract void removeConnection(String name);

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' + '\n' +
                ", year=" + year + '\n' +
                ", image='" + image + '\'' + '\n' +
                ", posts=" + posts +
                '}';
    }
}
