package app;
import java.util.ArrayList;
import java.util.List;

abstract public class Profile {
    private String name;
    private int year;
    private String image;
    private List<Post> posts;

    public Profile(String name, int year, String image, List<Post> posts) {
        this.name = name;
        this.year = year;
        this.image = image;
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getImage() {
        return image;
    }


}
