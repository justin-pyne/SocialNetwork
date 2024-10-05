import app.JsonProcessor;
import app.Profile;
import app.ProfileFactory;
import app.ProfileFactoryInterface;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonProcessorTest {
    JsonProcessor jp = new JsonProcessor();
    ProfileFactoryInterface factory = new ProfileFactory();

    @Test
    public void testParseProfiles(){
        List<Profile> profiles = jp.parseProfiles("./testProfile.json", factory);

        assertEquals("user", profiles.get(0).getType());
        assertEquals("Helen", profiles.get(0).getName());
        assertEquals(1990, profiles.get(0).getYear());
        assertEquals("helen.png", profiles.get(0).getImage());

        assertEquals("user", profiles.get(1).getType());
        assertEquals("Jenny", profiles.get(1).getName());
        assertEquals(2000, profiles.get(1).getYear());
        assertEquals("jenny.png", profiles.get(1).getImage());
    }
}
