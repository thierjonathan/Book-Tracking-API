package joel.thierry.booknest.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import joel.thierry.booknest.dto.BookDTO;
import joel.thierry.booknest.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public BookDTO convertToBookDTO(Book book) {
        Book.VolumeInfo volumeInfo = book.getVolumeInfo();
        return new BookDTO(
                book.getId(),
                volumeInfo.getTitle(),
                volumeInfo.getAuthors(),
                volumeInfo.getDescription(),
                volumeInfo.getPageCount(),
                volumeInfo.getAverageRating(),
                (volumeInfo.getImageLinks() !=null)? volumeInfo.getImageLinks().getThumbnail() : "",
                volumeInfo.getLanguage()
        );
    }

}
