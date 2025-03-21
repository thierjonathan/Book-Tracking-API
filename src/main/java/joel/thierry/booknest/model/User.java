package joel.thierry.booknest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Document(collation = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;

    private List<String> favoriteBook = new ArrayList<>();
    private List<String> favoriteAuthor = new ArrayList<>();
}
