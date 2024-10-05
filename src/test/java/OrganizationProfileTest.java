import app.OrganizationProfile;
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

public class OrganizationProfileTest {
    private OrganizationProfile testOrg;

    @BeforeEach
    public void setUp(){
        testOrg = new OrganizationProfile("testOrg", 2000, "testOrg.png", new ArrayList<>(), "123-123-1234", "30 Test Street", new ArrayList<>());
    }

    @Test
    public void testAddSupporter(){
        testOrg.addConnection("newSupporter");

        List<String> supporters = testOrg.getSupporters();
        assertEquals(1, supporters.size());
        assertEquals("newSupporter", supporters.get(0));
    }

    @Test
    public void testRemoveSupporter(){
        testOrg.addConnection("supporter1");
        testOrg.addConnection("supporter2");
        testOrg.removeConnection("supporter2");

        List<String> supporters = testOrg.getSupporters();
        assertEquals(1, supporters.size());
        assertEquals("supporter1", supporters.get(0));
    }

    @Test
    public void testAddDuplicateSupporter(){
        testOrg.addConnection("supporter1");
        testOrg.addConnection("supporter1");

        List<String> supporters = testOrg.getSupporters();
        assertEquals(1, supporters.size());
        assertEquals("supporter1", supporters.get(0));
    }

    @Test
    public void testRemoveFalseSupporter(){
        testOrg.addConnection("supporter1");
        testOrg.removeConnection("supporter2");

        List<String> supporters = testOrg.getSupporters();
        assertEquals(1, supporters.size());
        assertEquals("supporter1", supporters.get(0));
    }

    @Test
    public void testAddOrgPost(){
        testOrg.addPost("Testing adding a post.");
        List<Post> posts = testOrg.getPosts();
        assertEquals(1, posts.size());
        assertEquals("Testing adding a post.", posts.get(0).getMessage());
    }

    @Test
    public void testOrgSerializePostList(){
        String testJsonString = "[" +
                "{\"message\":\"Post1\",\"timestamp\":\"2024-10-04 12:00:00\"}," +
                "{\"message\":\"Post2\",\"timestamp\":\"2024-10-04 13:00:00\"}" +
                "]";
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Post1", "2024-10-04 12:00:00", "Justin"));
        posts.add(new Post("Post2", "2024-10-04 13:00:00", "Justin"));
        List<String> supporters = new ArrayList<>();

        OrganizationProfile testProfile = new OrganizationProfile("testOrg", 2000, "testOrg.png", posts, "123-123-1234", "30 Test Street", supporters);
        JsonArray serializedPosts = testProfile.serializePostList();

        assertEquals(2, serializedPosts.size());
        assertEquals(testJsonString, serializedPosts.toString());
    }

    @Test
    public void testOrgParsePosts(){
        String testJsonString = "{" +
                "\"type\":\"organization\"," +
                "\"name\":\"testOrg\"," +
                "\"year\":2000," +
                "\"image\":\"testOrg.png\"," +
                "\"posts\":[" +
                "{\"message\":\"Post1\",\"timestamp\":\"2024-10-04 12:00:00\"}," +
                "{\"message\":\"Post2\",\"timestamp\":\"2024-10-04 13:00:00\"}" +
                "]," +
                "\"supporters\":[\"supporter1\",\"supporter2\"]" +
                "}";
        JsonObject jsonObject = JsonParser.parseString(testJsonString).getAsJsonObject();
        List<Post> posts = UserProfile.parsePosts(jsonObject, "testOrg");

        assertEquals(2, posts.size());
        assertEquals("Post1", posts.get(0).getMessage());
        assertEquals("2024-10-04 12:00:00", posts.get(0).getTimestamp());
        assertEquals("testOrg", posts.get(0).getAuthor());
        assertEquals("Post2", posts.get(1).getMessage());
        assertEquals("2024-10-04 13:00:00", posts.get(1).getTimestamp());
        assertEquals("testOrg", posts.get(1).getAuthor());
    }

    @Test
    public void testSerializeOrgProfile(){
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Post1", "2024-10-04 12:00:00", "testOrg"));
        posts.add(new Post("Post2", "2024-10-04 13:00:00", "testOrg"));

        List<String> supporters = new ArrayList<>();
        supporters.add("friend1");
        supporters.add("friend2");

        OrganizationProfile testProfile = new OrganizationProfile("testOrg", 2000, "testOrg.png", posts, "123-123-1234", "30 Test Street", supporters);

        JsonObject serializedProfile = testProfile.serializeProfile();
        String expectedJsonString = "{" +
                "\"type\":\"organization\"," +
                "\"name\":\"testOrg\"," +
                "\"year\":2000," +
                "\"image\":\"testOrg.png\"," +
                "\"phone\":\"123-123-1234\"," +
                "\"address\":\"30 Test Street\"," +
                "\"posts\":[" +
                "{\"message\":\"Post1\",\"timestamp\":\"2024-10-04 12:00:00\"}," +
                "{\"message\":\"Post2\",\"timestamp\":\"2024-10-04 13:00:00\"}" +
                "]," +
                "\"supporters\":[\"supporter1\",\"supporter2\"]" +
                "}";

        String testJsonString = serializedProfile.toString();
        assertEquals(expectedJsonString, testJsonString);
    }
}
