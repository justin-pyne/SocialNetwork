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
        for (JsonElement friendEle : supporterArray){
            String name = friendEle.getAsString();
            supporterArray.add(name);
        }
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String getType() {
        return "organization";
    }

    @Override
    public void addConnection(String name) {

    }

    @Override
    public void removeConnection(String name) {

    }
}
