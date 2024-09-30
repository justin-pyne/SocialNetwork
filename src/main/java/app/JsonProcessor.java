package app;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class JsonProcessor {
    public static void main(String[] args){
//        JsonProcessor jp = new JsonProcessor();
//        Map<String, Profile> testMap = jp.parseProfiles("./profiles.json");
//        System.out.println(testMap);
//        SocialNetwork socialNetwork = new SocialNetwork();
//        socialNetwork.loadJson("./profiles.json");
//        String username = "Helen";
//        char[] password = "hel1990".toCharArray();
//        char[] password2 = "hel2000".toCharArray();
//        socialNetwork.auth(username, password);
//        socialNetwork.auth(username, password2);
    }

    public Map<String, Profile> parseProfiles(String fileName){
        Map<String, Profile> profileMap = new HashMap();

        try (FileReader fr = new FileReader(fileName)){
            JsonParser parser = new JsonParser();
            JsonObject rootObj = (JsonObject)parser.parse(fr);
            JsonArray socNet = rootObj.getAsJsonArray("socialNetwork");
            ProfileFactory factory = new ProfileFactory();

            for (JsonElement ele : socNet){
                JsonObject obj = (JsonObject)ele;
                String type = obj.get("type").getAsString();
                String name = obj.get("name").getAsString();
                Profile profile = factory.createProfile(type, obj);
                profileMap.put(name, profile);
            }
            System.out.println("Successfully parsed profiles.");
            return profileMap;

        } catch (Exception e){
            System.out.println("Failed to parse the profiles: " + e);
            e.printStackTrace();;
        }

        return null;
    }
}
