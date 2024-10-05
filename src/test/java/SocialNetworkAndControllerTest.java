import app.*;
import com.google.gson.JsonArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SocialNetworkAndControllerTest {
    private SocialNetwork socNet = new SocialNetwork();
    private ProfileFactoryInterface factory = new ProfileFactory();
    private SocialNetworkController controller = new SocialNetworkController(socNet);


    @BeforeEach
    public void setUp(){
        socNet.loadJson("./testProfile.json", factory);
    }


    @Test
    public void testTrueAuth(){
        boolean authResult = controller.auth("Helen", "hel1990".toCharArray());
        assertTrue(authResult);
    }

    @Test
    public void testFalseAuth(){
        boolean authResult = controller.auth("FalseUser", "falsepass".toCharArray());
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
        socNet2.loadJson("./testProfile.json", factory);
        SocialNetworkController controller2 = new SocialNetworkController(socNet2);
        controller2.addPost("Helen", "Test post.");
        assertEquals(3, socNet2.getProfile("Helen").getPosts().size());
        assertEquals("Test post.", socNet2.getProfile("Helen").getPosts().get(2).getMessage());
        assertEquals("Helen", socNet2.getProfile("Helen").getPosts().get(2).getAuthor());
    }

    @Test
    public void testAddFriend(){
        controller.addFriend("Helen", "Jenny");
        assertTrue(((UserProfile)(socNet.getProfile("Helen"))).getFriends().contains("Jenny"));
        controller.removeFriend("Helen", "Jenny");
    }

    @Test
    public void testRemoveFriend(){
        controller.removeFriend("Helen", "Cody");
        assertFalse(((UserProfile)(socNet.getProfile("Helen"))).getFriends().contains("Cody"));
        controller.addFriend("Helen", "Cody");

    }

    @Test
    public void testAddAndRemoveSupporters(){
        controller.addSupporter("Helen", "San Francisco Opera");
        assertTrue(((OrganizationProfile)(socNet.getProfile("San Francisco Opera"))).getSupporters().contains("Helen"));
        controller.removeSupporter("Helen", "San Francisco Opera");
        assertFalse(((OrganizationProfile)(socNet.getProfile("San Francisco Opera"))).getSupporters().contains("Helen"));
    }

    @Test
    public void testSerializeSocialNetwork(){
        JsonArray testJson = socNet.serializeSocialNetwork();
        String testString = testJson.toString();
        String expectedJsonString = "[{\"type\":\"user\",\"name\":\"Helen\",\"year\":1990,\"image\":\"helen.png\",\"posts\":[{\"message\":\"We did it! We hiked half-dome\",\"timestamp\":\"2024-09-23 11:56:02\"},{\"message\":\"Hiking in Yosemite\",\"timestamp\":\"2024-09-23 09:05:27\"}],\"friends\":[\"Cody\",\"Dipika\",\"Xiaofeng\"]},{\"type\":\"user\",\"name\":\"Jenny\",\"year\":2000,\"image\":\"jenny.png\",\"posts\":[{\"message\":\"Studying for the midterm\",\"timestamp\":\"2024-09-22 21:47:02\"}],\"friends\":[\"Cody\",\"Xiaofeng\"]},{\"type\":\"organization\",\"name\":\"San Francisco Opera\",\"year\":1899,\"image\":\"sfopera.png\",\"phone\":\"(415) 864-3330\",\"address\":\"301 Van Ness Ave, San Francisco, CA\",\"posts\":[{\"message\":\"Drinks & Drama: A Taste of Don Giovanni\",\"timestamp\":\"2024-09-23 22:47:12\"},{\"message\":\"Bring your whole family to Carmen for families\",\"timestamp\":\"2024-09-23 20:16:04\"},{\"message\":\"Tenor Hymel performs tonight\",\"timestamp\":\"2024-09-23 19:55:28\"}],\"supporters\":[]}]";
        assertEquals(expectedJsonString, testString);
    }
}
