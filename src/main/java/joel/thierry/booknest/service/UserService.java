package joel.thierry.booknest.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import joel.thierry.booknest.mapper.Mapper;
import joel.thierry.booknest.dto.BookDTO;
import joel.thierry.booknest.model.Book;
import joel.thierry.booknest.model.User;
import joel.thierry.booknest.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private final WebClient webClient;
    private final Mapper mapper;

    public UserService(UserRepository userRepository, WebClient.Builder webClientBuilder, Mapper mapper) {
        this.userRepository = userRepository;
        this.webClient = webClientBuilder.baseUrl("https://www.googleapis.com/books/v1").build();
        this.mapper = mapper;
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User addFavoriteBook(String username, String BookId){
        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        //makes sure there is no duplicate
        if(!user.getFavoriteBook().contains(BookId)) {
            user.getFavoriteBook().add(BookId);
        }
        return userRepository.save(user);

    }

    public User addFavoriteAuthor(String username, String Authorname){
        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        if(!user.getFavoriteAuthor().contains(Authorname)) {
            user.getFavoriteAuthor().add(Authorname);
        }
        return userRepository.save(user);

    }

    public User removeFavoriteBook(String username, String BookId){
        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        user.getFavoriteBook().remove(BookId);
        return userRepository.save(user);
    }

    public User removeFavoriteAuthor(String username, String Authorname){
        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        user.getFavoriteAuthor().remove(Authorname);
        return userRepository.save(user);
    }


    public List<BookDTO> getFavoriteBook(String username){
        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        List<String> bookIds = user.getFavoriteBook();

        List<BookDTO> books = new ArrayList<>();

        for(String bookId : bookIds) {
            String Url = "/volumes/" + bookId;
            Book book = webClient.get()
                    .uri(Url)
                    .retrieve()
                    .bodyToMono(Book.class)
                    .block();

            if(book != null) {
                books.add(mapper.convertToBookDTO(book));
            }

        }
        return books;
    }
}
