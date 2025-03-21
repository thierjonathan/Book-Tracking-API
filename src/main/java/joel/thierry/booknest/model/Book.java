package joel.thierry.booknest.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor   // ✅ Generates a no-args constructor (important for JSON)
@Data
//unfortunately lombok doesnt work
public class Book {
    private String id;
    private String title;
    private List<String> authors;
    private String description;
    private int pageCount;
    private double averageRating;
    private String thumbnail;
    private String language;

    // ✅ Manually add constructor for debugging (if Lombok is not working)
    public Book(String id, String title, List<String> authors, String description,
                int pageCount, double averageRating, String thumbnail, String language) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.pageCount = pageCount;
        this.averageRating = averageRating;
        this.thumbnail = thumbnail;
        this.language = language;
    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getLanguage() {
        return language;
    }


}
