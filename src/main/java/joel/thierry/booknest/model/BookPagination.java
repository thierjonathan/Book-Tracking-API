package joel.thierry.booknest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//unfortunately lombok doesnt work
public class BookSearchResponse {
    private int totalResults;
    private List<Book> books;


    // ✅ Add a constructor that matches the call in BookController
    public BookSearchResponse(List<Book> books, int totalResults) {
        this.books = books;
        this.totalResults = totalResults;
    }

    // ✅ No-arg constructor (needed for frameworks like Jackson)
    public BookSearchResponse() {
    }

    // ✅ Getters and setters
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }


}


