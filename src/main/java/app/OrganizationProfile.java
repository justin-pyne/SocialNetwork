package app;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A Profile object representing an Organization on the Social Network
 */
public class OrganizationProfile extends Profile {

    private List<String> supporters = new ArrayList<>();
    private String phone;
    private String address;

    /**
     * Constructor for an Organization Profile
     * @param name name of Organization
     * @param year year of Organization
     * @param image image filename
     * @param posts list of posts
     * @param phone phone number for organization
     * @param address address of organization
     * @param supporters list of supporters
     */
    public OrganizationProfile(String name, int year, String image, List<Post> posts, String phone, String address, List<String> supporters) {
        super(name, year, image, posts);
        this.phone = phone;
        this.address = address;
        this.supporters = supporters;
    }

    /**
     * Getter for phone number
     * @return Phone number for this organization
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Getter for address
     * @return Address of this organization
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter for supporter list
     * @return A copy of the supporter list for this organization
     */
    public List<String> getSupporters() {
        return List.copyOf(supporters);
    }

    /**
     * Getter for type of this profile
     * @return Type of the profile
     */
    @Override
    public String getType() {
        return "organization";
    }

    /**
     * Adds a supporter to the supporter list of this organization
     * @param name Name of the supporter to be added
     */
    @Override
    public void addConnection(String name) {
        if (!supporters.contains(name)){
            supporters.add(name);
        }
    }

    /**
     * Removes a supporter from the supporter list of this organization
     * @param name Name of the supporter to be removed
     */
    @Override
    public void removeConnection(String name) {
        supporters.remove(name);
    }

    /**
     * Serializes this OrganizationProfile into a JsonObject representation
     * @return A JsonObject representing this OrganizationProfile
     */
    @Override
    public JsonObject serializeProfile() {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "organization");
        obj.addProperty("name", this.getName());
        obj.addProperty("year", this.getYear());
        obj.addProperty("image", this.getImage());
        obj.addProperty("phone", this.phone);
        obj.addProperty("address", this.address);
        obj.add("posts", serializePostList());
        obj.add("supporters", serializeSupporters());
        return obj;
    }

    /**
     * Private helper to serialize the supporter list into a JsonArray representation
     * @return A JsonArray representation of the supporter list for this organization
     */
    private JsonArray serializeSupporters(){
        JsonArray jsonArr = new JsonArray();
        for (String supporter : supporters){
            jsonArr.add(supporter);
        }
        return jsonArr;
    }

    /**
     * toString for this organization
     * @return A string representation of this OrganizationProfile
     */
    @Override
    public String toString() {
        return "OrganizationProfile{" + super.toString() + '\'' + '\n' +
                "supporters=" + supporters + '\n' +
                ", phone='" + phone + '\'' + '\n' +
                ", address='" + address + '\'' + '\n' +
                '}';
    }
}
