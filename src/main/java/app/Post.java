package app;

public class Post {
    private String message;
    private String timestamp;
    private String author;

    public Post(String message, String timestamp, String author) {
        this.message = message;
        this.timestamp = timestamp;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Post{" +
                "message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
