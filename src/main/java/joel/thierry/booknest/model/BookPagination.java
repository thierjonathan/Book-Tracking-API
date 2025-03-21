package joel.thierry.booknest.model;

import java.util.List;
//unfortunately lombok doesnt work
public class BookPagination {
    private int totalResults;
    private List<Book> books;


    // ✅ Add a constructor that matches the call in BookController
    public BookPagination(List<Book> books, int totalResults) {
        this.books = books;
        this.totalResults = totalResults;
    }

    // ✅ No-arg constructor (needed for frameworks like Jackson)
    public BookPagination() {
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


