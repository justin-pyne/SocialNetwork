package app;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class OrganizationProfile extends Profile {

    private List<String> supporters = new ArrayList<>();
    private String phone;
    private String address;

    public OrganizationProfile(JsonObject obj) {
        super(obj);
        this.phone = obj.get("phone").getAsString();
        this.address = obj.get("address").getAsString();
        JsonArray supporterArray = obj.getAsJsonArray("supporters");
        for (JsonElement supporterEle : supporterArray){
            String name = supporterEle.getAsString();
            supporters.add(name);
        }
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getSupporters() {
        return List.copyOf(supporters);
    }

    @Override
    public String getType() {
        return "organization";
    }

    @Override
    public void addConnection(String name) {
        if (!supporters.contains(name)){
            supporters.add(name);
        }
    }

    @Override
    public void removeConnection(String name) {
        supporters.remove(name);
    }

    @Override
    public JsonObject serializeProfile() {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "user");
        obj.addProperty("name", this.getName());
        obj.addProperty("year", this.getYear());
        obj.addProperty("image", this.getImage());
        obj.addProperty("phone", this.phone);
        obj.addProperty("address", this.address);
        obj.add("posts", serializePostList());
        obj.add("supporters", serializeSupporters());
        return obj;
    }

    public JsonArray serializeSupporters(){
        JsonArray jsonArr = new JsonArray();
        for (String supporter : supporters){
            jsonArr.add(supporter);
        }
        return jsonArr;
    }

    @Override
    public String toString() {
        return "OrganizationProfile{" + super.toString() + '\'' + '\n' +
                "supporters=" + supporters + '\n' +
                ", phone='" + phone + '\'' + '\n' +
                ", address='" + address + '\'' + '\n' +
                '}';
    }
}
