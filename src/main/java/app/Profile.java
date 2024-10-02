package app;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * Abstract Profile class representing the general data and functionalities of a profile in the Social Network
 */
abstract public class Profile {
    private String name;
    private int year;
    private String image;
    private List<Post> posts;

    /**
     * Constructor for general Profile objects
     * @param obj A JsonObject representation of the Profile
     */
    public Profile(JsonObject obj) {
        this.name = obj.get("name").getAsString();
        this.year = obj.get("year").getAsInt();
        this.image = obj.get("image").getAsString();
        this.posts = parsePosts(obj);
    }

    /**
     * parses posts from a given Json Object and adds it to a List of Post objects for this profile.
     * @param obj JsonObject holding the posts of this profile
     * @return A list of posts for this profile
     */
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


    /**
     * Getter for name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for year
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * Getter for image
     * @return
     */
    public String getImage() {
        return image;
    }

    /**
     * Getter for list of posts of this profile
     * @return A list of the posts by this profile
     */
    public List<Post> getPosts() {
        return List.copyOf(posts);
    }


    /**
     * Serializes a Post object into a JsonObject representation.
     * @param post The Post object to be processed and serialized
     * @return A JsonObject representing the post
     */
    public JsonObject serializePost(Post post){
        JsonObject obj = new JsonObject();
        obj.addProperty("message", post.getMessage());
        obj.addProperty("timestamp", post.getTimestamp());
        return obj;
    }


    /**
     * Serializes the list of posts made by this Profile.
     * @return A JsonArray of JsonObjects representing the posts
     */
    public JsonArray serializePostList(){
        JsonArray JsonArr = new JsonArray();
        for (Post post : this.getPosts()){
            JsonObject postObj = serializePost(post);
            JsonArr.add(postObj);
        }
        return JsonArr;
    }

    /**
     * Creates and adds a Post to the posts Arraylist of this Profile
     * @param message The message content of the post
     */
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
