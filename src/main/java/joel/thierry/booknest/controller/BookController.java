package joel.thierry.booknest.controller;

import joel.thierry.booknest.model.Book;
import joel.thierry.booknest.model.BookSearchResponse;
import joel.thierry.booknest.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService; //dependency injection
    }

    @GetMapping("/{title}")
    public BookSearchResponse searchBooks(@PathVariable String title) {

        List<Book> books = bookService.getBooksByTitle(title);
        return new BookSearchResponse(books, books.size());
    }

}
