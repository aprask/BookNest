package bookagg.api.home.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity(name = "books")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "pages", nullable = false)
    private int pages;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "cover_image_url", nullable = false)
    private String coverImageUrl;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "rating")
    private double rating;

    @Column(name = "average_review", nullable = false)
    private double averageReview;

    @Column(name = "dimensions", nullable = false)
    private String dimensions;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "shipping_weight", nullable = false)
    private String shippingWeight;

    @Column(name = "bestseller", nullable = false)
    private boolean bestseller;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Book book = (Book) o;
        return getId() != null && Objects.equals(getId(), book.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
