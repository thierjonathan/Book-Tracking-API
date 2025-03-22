package joel.thierry.booknest.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;

    private List<String> favoriteBook = new ArrayList<>();
    private List<String> favoriteAuthor = new ArrayList<>();

    public User() {}
    public User(String username, String password, List<String> favoriteBook, List<String> favoriteAuthor) {
        this.username = username;
        this.password = password;
        this.favoriteBook = favoriteBook;
        this.favoriteAuthor = favoriteAuthor;
    }

}
