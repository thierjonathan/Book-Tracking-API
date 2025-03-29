package joel.thierry.booknest.controller;

import joel.thierry.booknest.dto.BookDTO;
import joel.thierry.booknest.model.Book;
import joel.thierry.booknest.model.BookPagination;
import joel.thierry.booknest.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService; //dependency injection
    }


    @GetMapping("/{title}")
    public BookPagination searchBooks(@PathVariable String title) {

        List<BookDTO> books = bookService.getBooksByTitle(title);
        return new BookPagination(books.size(), books);
    }
}
