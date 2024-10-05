package app;

public interface Controller {
    boolean auth(String username, char[] password);
    void addPost(String username, String message);
    void addConnection(String username, String friend);
    void removeConnection(String username, String friend);
}
