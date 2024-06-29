package bookagg.api.home.service;

import bookagg.api.home.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BookService {

    void processBook(Book book);
    Book createBook(String message);
    Optional<Book> getBookById(Long id);
    Page<Book> getAllBooks(Pageable pageable);
    Page<Book> getBooksByTitle(String title, Pageable pageable);
    Page<Book> getBooksByAuthor(String author, Pageable pageable);
    Book updateBook(Long id, String message);
    void deleteBook(Long id);


}
