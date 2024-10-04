import app.OrganizationProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class OrganizationProfileTest {
    private OrganizationProfile testOrg;

    @BeforeEach
    public void setUp(){
        testOrg = new OrganizationProfile("testOrg", 2000, "testOrg.png", new ArrayList<>(), "123-123-1234", "30 Test Street", new ArrayList<>());
    }

    @Test
    public void testAddSupporter(){

    }

    @Test
    public void testRemoveSupporter(){

    }

    @Test
    public void testAddDuplicateSupporter(){

    }

    @Test
    public void testRemoveFalseSupporter(){

    }

    @Test
    public void testAddOrgPost(){

    }

    @Test
    public void testOrgSerializePostList(){

    }

    @Test
    public void testOrgParsePosts(){

    }

    @Test
    public void testSerializeOrgProfile(){

    }
}
