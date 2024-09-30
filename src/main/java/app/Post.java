package app;

public class Post {
    private String message;
    private String timestamp;

    public Post(String message, String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "Post{" +
                "message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
