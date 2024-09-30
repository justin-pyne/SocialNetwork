package app;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class JsonProcessor {
    public static void main(String[] args){
        JsonProcessor jp = new JsonProcessor();
        jp.parseProfiles("src/profiles.json");
    }

    public void parseProfiles(String fileName){
        try (FileReader fr = new FileReader(fileName)){
            JsonParser parser = new JsonParser();
            JsonObject rootObj = (JsonObject)parser.parse(fr);
            JsonArray socNet = rootObj.getAsJsonArray("socialNetwork");
            ProfileFactory factory = new ProfileFactory();

            for (JsonElement ele : socNet){
                JsonObject obj = (JsonObject)ele;

            }


        } catch (Exception e){
            System.out.println("Failed to parse the profiles: " + e);
        }
    }
}
