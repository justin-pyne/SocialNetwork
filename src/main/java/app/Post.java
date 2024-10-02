package app;


/**
 * A class representing a post on the Social Network
 */
public class Post {
    private String message;
    private String timestamp;
    private String author;

    /**
     * Constructor for Post objects
     * @param message The text content of the post
     * @param timestamp The time the post was created
     * @param author The author of the post
     */
    public Post(String message, String timestamp, String author) {
        this.message = message;
        this.timestamp = timestamp;
        this.author = author;
    }

    /**
     * Getter for the message
     * @return Message of the post
     */
    public String getMessage() {
        return message;
    }

    /**
     * Getter for the timestamp
     * @return Timestamp of the post
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Getter for the author
     * @return Author of the post
     */
    public String getAuthor() {
        return author;
    }

    /**
     * toString for this Post object
     * @return A string representation of this Post
     */
    @Override
    public String toString() {
        return "Post{" +
                "message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
