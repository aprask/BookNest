package gamescout.api.dashboard.service;

import gamescout.api.dashboard.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface BookService {

    void processBook(Book book);
    Book createBook(String message);
    Optional<Book> getBookById(UUID id);
    Page<Book> getAllBooks(Pageable pageable);
    Page<Book> getBooksByTitle(String title, Pageable pageable);
    Page<Book> getBooksByAuthor(String author, Pageable pageable);
    Book updateBook(UUID id, String message);
    void deleteBook(UUID id);


}
