package app;

import com.google.gson.JsonObject;
import com.sun.jdi.InvalidTypeException;


/**
 * A class that creates different Profile objects based on the type of profile.
 */
public class ProfileFactory implements ProfileFactoryInterface{


    /**
     * Creates the appropriate Profile object based on the type of profile.
     * @param type The desired type of profile
     * @param obj A JsonObject representing the profile
     * @return Profile - The appropriate profile object
     * @throws InvalidTypeException
     */
    @Override
    public Profile createProfile(String type, JsonObject obj) throws InvalidTypeException {
        if (type.equals("user")){
            return new UserProfile(obj);
        } else if (type.equals("organization")) {
            return new OrganizationProfile(obj);
        } else {
            throw new InvalidTypeException();
        }
    }
}
