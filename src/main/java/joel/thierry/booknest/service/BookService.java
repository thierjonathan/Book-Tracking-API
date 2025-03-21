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

import java.util.List;

@Service
public class BookService {
    private final WebClient webClient;
    private final ObjectMapper jacksonObjectMapper;

    public BookService(WebClient.Builder webClientBuilder, ObjectMapper jacksonObjectMapper) {
        this.webClient = webClientBuilder.baseUrl("https://www.googleapis.com/books/v1").build();
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    public List<Book> getBooksByTitle(String title) {
        String titleURL = "/volumes?q=intitle:" + title; // Wrap title in quotes
        return webClient.get()
                .uri(titleURL)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::convertJsonToList)
                .block();
    }

    private List<Book> convertJsonToList(String ApiResponse) {
        List<Book> books = new ArrayList<>();
        try{
            JsonNode base = jacksonObjectMapper.readTree(ApiResponse);
            JsonNode items = base.get("items");

            if(items != null){
                for(JsonNode item : items){
                    JsonNode volumeInfo = item.get("volumeInfo");

                    books.add(new Book(
                            item.get("id").asText(),
                            volumeInfo.get("title").asText(),
                            volumeInfo.has("authors") ?
                                    StreamSupport.stream(volumeInfo.get("authors").spliterator(), false)
                                            .map(JsonNode::asText)
                                            .collect(Collectors.toList()) : new ArrayList<>(), //if author is missing it returns an emnpty List
                            volumeInfo.has("description") ? volumeInfo.get("description").asText() : "",
                            volumeInfo.has("pageCount") ? volumeInfo.get("pageCount").asInt() : 0,
                            volumeInfo.has("averageRating") ? volumeInfo.get("averageRating").asDouble() : 0.0,
                            volumeInfo.has("imageLinks") && volumeInfo.get("imageLinks").has("thumbnail") ?
                                    volumeInfo.get("imageLinks").get("thumbnail").asText() : "",
                            volumeInfo.has("language") ? volumeInfo.get("language").asText() : ""
                    ));
                }
            }
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return books;
    }
}
