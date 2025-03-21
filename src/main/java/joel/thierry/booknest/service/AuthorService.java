package joel.thierry.booknest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
                .map(this::extractAuthors)
                .block();
    }


    private List<String> extractAuthors(String ApiResponse){
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

}
