package app;

import com.google.gson.JsonObject;
import com.sun.jdi.InvalidTypeException;

public interface ProfileFactoryInterface {
    Profile createProfile(String type, JsonObject obj) throws InvalidTypeException;
}
