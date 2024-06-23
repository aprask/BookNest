package gamescout.api.dashboard.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gamescout.api.dashboard.model.Book;
import gamescout.api.dashboard.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookListener {

    private final BookService bookService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = RabbitMQConfig.BOOKS_QUEUE)
    public void receive(String messages) {
        try {
            List<Book> books = objectMapper.readValue(objectMapper.writeValueAsString(messages), new TypeReference<>() {});

            for (Book book : books) {
                book.setId(UUID.randomUUID());
                bookService.processBook(book);
                log.info("Received book: {}", book.getTitle());
            }

        } catch (Exception e) {
            log.error("Error processing book message: {}", e.getMessage(), e);
        }
    }

}
