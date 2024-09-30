package app;

import com.google.gson.JsonObject;
import com.sun.jdi.InvalidTypeException;

public class ProfileFactory implements ProfileFactoryInterface{

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
