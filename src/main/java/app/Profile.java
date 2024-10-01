package app;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            Post post = new Post(message, timestamp, name);
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

    public List<Post> getPosts() {
        return List.copyOf(posts);
    }

    public JsonObject serializePost(Post post){
        JsonObject obj = new JsonObject();
        obj.addProperty("message", post.getMessage());
        obj.addProperty("timestamp", post.getTimestamp());
        return obj;
    }

    public JsonArray serializePostList(){
        JsonArray JsonArr = new JsonArray();
        for (Post post : this.getPosts()){
            JsonObject postObj = serializePost(post);
            JsonArr.add(postObj);
        }
        return JsonArr;
    }

    public void addPost(String message){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currTime = now.format(format);
        Post post = new Post(message, currTime, name);
        posts.add(post);
    }



    public abstract String getType();
    public abstract void addConnection(String name);
    public abstract void removeConnection(String name);
    public abstract JsonObject serializeProfile();

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
