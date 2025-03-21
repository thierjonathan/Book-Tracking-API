package joel.thierry.booknest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import joel.thierry.booknest.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorService {
    private final WebClient webClient;
    private final ObjectMapper jacksonObjectMapper;

    public AuthorService(WebClient.Builder webClientBuilder, ObjectMapper jacksonObjectMapper) {
        this.webClient = webClientBuilder.baseUrl("https://www.googleapis.com/books/v1").build();
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    public List<String> getAuthors(String author) {
        String authorURL = "/volumes?q=inauthor:\"" + author + "\""; // Wrap in quotes
        return webClient.get()
                .uri(authorURL)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> this.extractAuthors(response,author))
                .block();
    }

    public List<Book> getBooksByAuthor(String author) {
        String authorURL = "/volumes?q=inauthor:" + author;
        return webClient.get()
                .uri(authorURL)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> this.extractBooksFromAuthor(response,author))
                .block();
    }

    private List<String> extractAuthors(String ApiResponse, String authorName){
        List<String> authors = new ArrayList<>();
        try {
            JsonNode base = jacksonObjectMapper.readTree(ApiResponse);
            JsonNode items = base.get("items");

            if(items != null) {
                for (JsonNode item : items) {
                    JsonNode volumeInfo = item.get("volumeInfo");

                    if(volumeInfo.has("authors")) {
                        List<String> authorList = StreamSupport.stream(volumeInfo.get("authors").spliterator(), false)
                                .map(JsonNode::asText)
                                .filter(name -> name.toLowerCase().contains(authorName.toLowerCase()))
                                .collect(Collectors.toList());

                        for(String author : authorList){
                            if(!authors.contains(author)){
                                authors.add(author);
                            }
                        }
                    }
                }
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return authors;
    }

    public List<Book> extractBooksFromAuthor(String ApiResponse, String authorName){
        List<Book> books = new ArrayList<>();
        try {
            JsonNode base = jacksonObjectMapper.readTree(ApiResponse);
            JsonNode items = base.get("items");

            if (items != null) {
                for (JsonNode item : items) {
                    JsonNode volumeInfo = item.get("volumeInfo");

                    if (volumeInfo.has("authors")) {
                        List<String> authorList = StreamSupport.stream(volumeInfo.get("authors").spliterator(), false)
                                .map(JsonNode::asText)
                                .collect(Collectors.toList());

                        // Ensure the searched author is explicitly listed
                        if (authorList.stream().anyMatch(name -> name.equalsIgnoreCase(authorName))) {
                            books.add(new Book(
                                    item.get("id").asText(),
                                    volumeInfo.get("title").asText(),
                                    authorList, // List of authors
                                    volumeInfo.has("description") ? volumeInfo.get("description").asText() : "",
                                    volumeInfo.has("pageCount") ? volumeInfo.get("pageCount").asInt() : 0,
                                    volumeInfo.has("averageRating") ? volumeInfo.get("averageRating").asDouble() : 0.0,
                                    volumeInfo.has("imageLinks") && volumeInfo.get("imageLinks").has("thumbnail") ?
                                            volumeInfo.get("imageLinks").get("thumbnail").asText() : "",
                                    volumeInfo.has("language") ? volumeInfo.get("language").asText() : ""
                            ));
                        }
                    }
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

}
