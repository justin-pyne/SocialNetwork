package app;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.jdi.InvalidTypeException;

import java.util.ArrayList;
import java.util.List;


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
        String name = obj.get("name").getAsString();
        int year = obj.get("year").getAsInt();
        String image = obj.get("image").getAsString();
        List<Post> posts = Profile.parsePosts(obj, name);

        if (type.equals("user")){
            List<String> friends = new ArrayList<>();
            JsonArray friendsArray = obj.getAsJsonArray("friends");
            for (JsonElement friendEle : friendsArray) {
                String friendName = friendEle.getAsString();
                friends.add(friendName);
            }
            return new UserProfile(name, year, image, posts, friends);
        } else if (type.equals("organization")) {
            String phone = obj.get("phone").getAsString();
            String address = obj.get("address").getAsString();
            List<String> supporters = new ArrayList<>();
            JsonArray supporterArray = obj.getAsJsonArray("supporters");
            for (JsonElement supporterEle : supporterArray){
                String supporterName = supporterEle.getAsString();
                supporters.add(supporterName);
            }
            return new OrganizationProfile(name, year, image, posts, phone, address, supporters);
        } else {
            throw new InvalidTypeException();
        }
    }
}
