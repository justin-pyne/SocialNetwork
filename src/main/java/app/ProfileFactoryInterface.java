package app;

import com.google.gson.JsonObject;
import com.sun.jdi.InvalidTypeException;


/**
 * Interface for a Profile factory to create different types of profiles.
 */
public interface ProfileFactoryInterface {
    Profile createProfile(String type, JsonObject obj) throws InvalidTypeException;
}
