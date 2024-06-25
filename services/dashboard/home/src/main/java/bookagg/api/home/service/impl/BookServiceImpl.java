package bookagg.api.home.service.impl;

import bookagg.api.home.exceptions.MissingBookException;
import bookagg.api.home.model.Book;
import bookagg.api.home.repository.BookRepository;
import bookagg.api.home.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void processBook(Book book) {
        bookRepository.save(book);
        log.info("Saved book: {}", book.getTitle());
    }

    @Override
    public Book createBook(String message) {
        if (message == null) {
            throw new MissingBookException("Book is null and cannot be created");
        }
        try {
            Book book = objectMapper.readValue(message, Book.class);
            book.setId(new Random().nextLong());
            return bookRepository.save(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        if (id == null) {
            throw new MissingBookException("Book id is null and cannot be retrieved");
        }
        return bookRepository.findById(id);
    }

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        try {
            if (validatePageable(pageable)) {
                return bookRepository.findAll(pageable);
            }
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> getBooksByTitle(String title, Pageable pageable) {
        try {
            if (validatePageable(pageable)) {
                if(title != null) {
                    return bookRepository.findByTitle(title, pageable);
                }
            }
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Page<Book> getBooksByAuthor(String author, Pageable pageable) {
        if (author == null) {
            throw new MissingBookException("Author is null and cannot be retrieved");
        }
        return null;
    }

    @Override
    public Book updateBook(Long id, String message) {
        if (id == null) {
            throw new MissingBookException("Book id is null and cannot be updated");
        }
        if (message == null) {
            throw new MissingBookException("Message is null and cannot be mapped to book");
        }
        try {
            Book modifiedBook = bookRepository.findById(id).orElseThrow(() -> new MissingBookException("Could not find book"));
            Book book = objectMapper.readValue(message, Book.class);
            modifiedBook.setTitle(book.getTitle());
            modifiedBook.setAuthor(book.getAuthor());
            modifiedBook.setIsbn(book.getIsbn());
            modifiedBook.setGenre(book.getGenre());
            modifiedBook.setPublisher(book.getPublisher());
            modifiedBook.setYear(book.getYear());
            modifiedBook.setPages(book.getPages());
            modifiedBook.setDescription(book.getDescription());
            modifiedBook.setPublisher(book.getPublisher());
            modifiedBook.setCoverImageUrl(book.getCoverImageUrl());
            modifiedBook.setPrice(book.getPrice());
            modifiedBook.setShippingWeight(book.getShippingWeight());
            modifiedBook.setDimensions(book.getDimensions());
            modifiedBook.setWeight(book.getWeight());
            modifiedBook.setBestseller(book.isBestseller());
            modifiedBook.setRating(book.getRating());
            return bookRepository.save(modifiedBook);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(Long id) {
        if (id == null) {
            throw new MissingBookException("Book id is null and cannot be deleted");
        }
        bookRepository.deleteById(id);
    }

    private boolean validatePageable(Pageable pageable) throws BadRequestException {
        return pageable.getPageSize() >= 0 && pageable.getOffset() > 0;
    }

}
