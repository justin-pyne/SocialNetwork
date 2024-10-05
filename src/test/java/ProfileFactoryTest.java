import app.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.jdi.InvalidTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ProfileFactoryTest {
    private ProfileFactoryInterface factory = new ProfileFactory();
    private JsonObject userProfile;
    private JsonObject orgProfile;
    @BeforeEach
    public void setUp(){
        userProfile = new JsonObject();
        orgProfile = new JsonObject();
        userProfile.addProperty("type", "user");
        userProfile.addProperty("name", "Justin");
        userProfile.addProperty("year", 2000);
        userProfile.addProperty("image", "justin.png");
        userProfile.add("posts", new JsonArray());
        userProfile.add("friends", new JsonArray());

        orgProfile.addProperty("type", "organization");
        orgProfile.addProperty("name", "testOrg");
        orgProfile.addProperty("year", 1999);
        orgProfile.addProperty("image", "orgimage.png");
        orgProfile.add("posts", new JsonArray());
        orgProfile.addProperty("phone", "123-123-1234");
        orgProfile.addProperty("address", "30 Test Street");
        orgProfile.add("supporters", new JsonArray());
    }

    @Test
    public void testCreateUserProfile() throws InvalidTypeException {
        Profile profile = factory.createProfile("user", userProfile);
        assertInstanceOf(UserProfile.class, profile);
        assertEquals("user", profile.getType());
        assertEquals("Justin", profile.getName());
        assertEquals(2000, profile.getYear());
        assertEquals("justin.png", profile.getImage());
    }

    @Test
    public void testCreateOrganizationProfile() throws InvalidTypeException {
        Profile profile = factory.createProfile("organization", orgProfile);
        assertInstanceOf(OrganizationProfile.class, profile);
        assertEquals("organization", profile.getType());
        assertEquals("testOrg", profile.getName());
        assertEquals(1999, profile.getYear());
        assertEquals("orgimage.png", profile.getImage());
        assertEquals("123-123-1234", ((OrganizationProfile)profile).getPhone());
        assertEquals("30 Test Street", ((OrganizationProfile)profile).getAddress());
    }
}
