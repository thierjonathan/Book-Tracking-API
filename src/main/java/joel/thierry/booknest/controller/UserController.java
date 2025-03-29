package joel.thierry.booknest.controller;

import joel.thierry.booknest.dto.BookDTO;
import joel.thierry.booknest.model.Book;
import joel.thierry.booknest.model.User;
import joel.thierry.booknest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{username}/favorite-books")
    public ResponseEntity<User> addFavoriteBook(@PathVariable String username, @RequestParam String bookId) {
        try{
            User updatedUser = userService.addFavoriteBook(username, bookId);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{username}/favorite-authors")
    public ResponseEntity<User> addFavoriteAuthor(@PathVariable String username, @RequestParam String authorName) {
        try{
            User updatedUser = userService.addFavoriteAuthor(username, authorName);
            return ResponseEntity.ok(updatedUser);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{username}/favorite-books/{bookId}")
    public ResponseEntity<User> removeFavoriteBook(@PathVariable String username, @PathVariable String bookId) {
        try {
            User updatedUser = userService.removeFavoriteBook(username, bookId);
            return ResponseEntity.ok(updatedUser);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{username}/favorite-authors/{bookId}")
    public ResponseEntity<User> removeFavoriteAuthor(@PathVariable String username, @PathVariable String bookId) {
        try {
            User updatedUser = userService.removeFavoriteAuthor(username, bookId);
            return ResponseEntity.ok(updatedUser);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{username}/favorite-books/get")
    public List<BookDTO> getFavoriteBook(@PathVariable String username) {
        try {
                List<BookDTO> books = userService.getFavoriteBook(username);
            return books;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
