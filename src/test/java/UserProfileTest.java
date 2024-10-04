import app.Post;
import app.UserProfile;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserProfileTest {
    private UserProfile testUser;

    @BeforeEach
    public void setUp(){
        testUser = new UserProfile("Justin", 2000, "justin.png", new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void testAddUserConnection(){
        testUser.addConnection("newFriend");

        List<String> friends = testUser.getFriends();
        assertEquals(1, friends.size());
        assertEquals("newFriend", friends.get(0));
    }

    @Test
    public void testRemoveUserConnection(){
        testUser.addConnection("friend1");
        testUser.addConnection("friend2");
        testUser.removeConnection("friend2");

        List<String> friends = testUser.getFriends();
        assertEquals(1, friends.size());
        assertEquals("friend1", friends.get(0));
    }

    @Test
    public void testAddDuplicateConnection(){
        testUser.addConnection("friend1");
        testUser.addConnection("friend1");

        List<String> friends = testUser.getFriends();
        assertEquals(1, friends.size());
        assertEquals("friend1", friends.get(0));
    }

    @Test
    public void testRemoveFalseConnection(){
        testUser.addConnection("friend1");
        testUser.removeConnection("friend2");

        List<String> friends = testUser.getFriends();
        assertEquals(1, friends.size());
        assertEquals("friend1", friends.get(0));
    }

    @Test
    public void testUserAddPost(){
        testUser.addPost("Testing adding a post.");
        List<Post> posts = testUser.getPosts();
        assertEquals(1, posts.size());
        assertEquals("Testing adding a post.", posts.get(0).getMessage());
    }

    @Test
    public void testUserSerializePostList(){
        String testJsonString = "[" +
                "{\"message\":\"Post1\",\"timestamp\":\"2024-10-04 12:00:00\"}," +
                "{\"message\":\"Post2\",\"timestamp\":\"2024-10-04 13:00:00\"}" +
                "]";
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Post1", "2024-10-04 12:00:00", "Justin"));
        posts.add(new Post("Post2", "2024-10-04 13:00:00", "Justin"));
        List<String> friends = new ArrayList<>();

        UserProfile testProfile = new UserProfile("Justin", 2000, "justin.png", posts, friends);
        JsonArray serializedPosts = testProfile.serializePostList();

        assertEquals(2, serializedPosts.size());
        assertEquals(testJsonString, serializedPosts.toString());
    }

    @Test
    public void testUserParsePosts(){
        String testJsonString = "{" +
                "\"type\":\"user\"," +
                "\"name\":\"Justin\"," +
                "\"year\":2000," +
                "\"image\":\"justin.png\"," +
                "\"posts\":[" +
                "{\"message\":\"Post1\",\"timestamp\":\"2024-10-04 12:00:00\"}," +
                "{\"message\":\"Post2\",\"timestamp\":\"2024-10-04 13:00:00\"}" +
                "]," +
                "\"friends\":[\"friend1\",\"friend2\"]" +
                "}";
        JsonObject jsonObject = JsonParser.parseString(testJsonString).getAsJsonObject();
        List<Post> posts = UserProfile.parsePosts(jsonObject, "Justin");

        assertEquals(2, posts.size());
        assertEquals("Post1", posts.get(0).getMessage());
        assertEquals("2024-10-04 12:00:00", posts.get(0).getTimestamp());
        assertEquals("Justin", posts.get(0).getAuthor());
        assertEquals("Post2", posts.get(1).getMessage());
        assertEquals("2024-10-04 13:00:00", posts.get(1).getTimestamp());
        assertEquals("Justin", posts.get(1).getAuthor());
    }

    @Test
    public void testSerializeUserProfile(){
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Post1", "2024-10-04 12:00:00", "Justin"));
        posts.add(new Post("Post2", "2024-10-04 13:00:00", "Justin"));

        List<String> friends = new ArrayList<>();
        friends.add("friend1");
        friends.add("friend2");

        UserProfile testProfile = new UserProfile("Justin", 2000, "justin.png", posts, friends);

        JsonObject serializedProfile = testProfile.serializeProfile();
        String expectedJsonString = "{" +
                "\"type\":\"user\"," +
                "\"name\":\"Justin\"," +
                "\"year\":2000," +
                "\"image\":\"justin.png\"," +
                "\"posts\":[" +
                "{\"message\":\"Post1\",\"timestamp\":\"2024-10-04 12:00:00\"}," +
                "{\"message\":\"Post2\",\"timestamp\":\"2024-10-04 13:00:00\"}" +
                "]," +
                "\"friends\":[\"friend1\",\"friend2\"]" +
                "}";

        String testJsonString = serializedProfile.toString();
        assertEquals(expectedJsonString, testJsonString);
    }
}
