package bookagg.api.home.repository;

import bookagg.api.home.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByIsbn(String isbn, Pageable pageable);
    Page<Book> findByAuthor(String author, Pageable pageable);
    Page<Book> findByGenre(String genre, Pageable pageable);
    Page<Book> findByPublisher(String publisher, Pageable pageable);
    Page<Book> findByTitleAndAuthor(String title, String author, Pageable pageable);
    Page<Book> findByTitleAndIsbn(String title, String isbn, Pageable pageable);
    Page<Book> findByTitleAndAuthorAndIsbn(String title, String author, String isbn, Pageable pageable);
    Page<Book> findByTitleAndAuthorAndIsbnAndGenre(String title, String author, String isbn, String genre, Pageable pageable);
    Page<Book> findByTitleAndAuthorAndIsbnAndGenreAndPublisher(String title, String author, String isbn, String genre, String publisher, Pageable pageable);
    Page<Book> findByTitleAndAuthorAndIsbnAndGenreAndPublisherAndYear(String title, String author, String isbn, String genre, String publisher, int year, Pageable pageable);
    Page<Book> findByTitleAndAuthorAndIsbnAndGenreAndPublisherAndYearAndPages(String title, String author, String isbn, String genre, String publisher, int year, int pages, Pageable pageable);
    Page<Book> findByTitleAndAuthorAndIsbnAndGenreAndPublisherAndYearAndPagesAndDescription(String title, String author, String isbn, String genre, String publisher, int year, int pages, String description, Pageable pageable);
    Page<Book> findByTitle(String title, Pageable pageable);
}
