package app;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class contains methods to parse profiles from a JSON and create a TreeMap of all the Profile objects.
 */
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

    /**
     * Parses a jsonfile of profiles, and puts profile objects into a treemap
     * @param fileName Name of json file containing profiles
     * @return profileMap TreeMap containing all the profiles parsed
     */
    public TreeMap<String, Profile> parseProfiles(String fileName){
        TreeMap<String, Profile> profileMap = new TreeMap();

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
