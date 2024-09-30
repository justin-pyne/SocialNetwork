package app;

import com.google.gson.JsonObject;

import java.util.List;

public class OrganizationProfile extends Profile {

    private String phone;
    private String address;

    public OrganizationProfile(JsonObject obj) {
        super(obj);
        this.phone = obj.get("phone").getAsString();
        this.address = obj.get("address").getAsString();
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
