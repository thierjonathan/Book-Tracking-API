package joel.thierry.booknest.model;

import joel.thierry.booknest.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookPagination {
    private int totalResults;
    private List<BookDTO> books;
}


