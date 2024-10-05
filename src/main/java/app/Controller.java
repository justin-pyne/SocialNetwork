package app;

public interface Controller {
    boolean auth(String username, char[] password);
    void addPost(String username, String message);
    void addFriend(String username, String friend);
    void removeFriend(String username, String friend);
    void addSupporter(String user, String org);
    void removeSupporter(String user, String org);
}
