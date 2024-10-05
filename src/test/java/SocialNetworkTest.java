import app.ProfileFactory;
import app.ProfileFactoryInterface;
import app.SocialNetwork;
import app.UserProfile;
import com.google.gson.JsonArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SocialNetworkTest {
    private SocialNetwork socNet = new SocialNetwork();
    private ProfileFactoryInterface factory = new ProfileFactory();


    @BeforeEach
    public void setUp(){
        socNet.loadJson("testProfile.json", factory);
    }


    @Test
    public void testTrueAuth(){
        boolean authResult = socNet.auth("Helen", "hel1990".toCharArray());
        assertTrue(authResult);
    }

    @Test
    public void testFalseAuth(){
        boolean authResult = socNet.auth("FalseUser", "falsepass".toCharArray());
        assertFalse(authResult);
    }

    @Test
    public void testContainsProfile(){
        assertTrue(socNet.containsProfile("Helen"));
        assertFalse(socNet.containsProfile("Justin"));
    }

    @Test
    public void testAddPost(){
        SocialNetwork socNet2 = new SocialNetwork();
        socNet2.loadJson("testProfile.json", factory);
        socNet2.addPost("Helen", "Test post.");
        assertEquals(3, socNet2.getProfile("Helen").getPosts().size());
        assertEquals("Test post.", socNet2.getProfile("Helen").getPosts().get(2).getMessage());
        assertEquals("Helen", socNet2.getProfile("Helen").getPosts().get(2).getAuthor());
    }

    @Test
    public void testAddConnection(){
        socNet.addConnection("Helen", "Jenny");
        assertTrue(((UserProfile)(socNet.getProfile("Helen"))).getFriends().contains("Jenny"));
        socNet.removeConnection("Helen", "Jenny");
    }

    @Test
    public void testRemoveConnection(){
        socNet.removeConnection("Helen", "Cody");
        assertFalse(((UserProfile)(socNet.getProfile("Helen"))).getFriends().contains("Cody"));
        socNet.addConnection("Helen", "Cody");

    }

    @Test
    public void testSerializeSocialNetwork(){
        JsonArray testJson = socNet.serializeSocialNetwork();
        String testString = testJson.toString();
        String expectedJsonString = "[{\"type\":\"user\",\"name\":\"Helen\",\"year\":1990,\"image\":\"helen.png\",\"posts\":[{\"message\":\"We did it! We hiked half-dome\",\"timestamp\":\"2024-09-23 11:56:02\"},{\"message\":\"Hiking in Yosemite\",\"timestamp\":\"2024-09-23 09:05:27\"}],\"friends\":[\"Cody\",\"Dipika\",\"Xiaofeng\"]},{\"type\":\"user\",\"name\":\"Jenny\",\"year\":2000,\"image\":\"jenny.png\",\"posts\":[{\"message\":\"Studying for the midterm\",\"timestamp\":\"2024-09-22 21:47:02\"}],\"friends\":[\"Cody\",\"Xiaofeng\"]}]";
        assertEquals(expectedJsonString, testString);
    }
}
