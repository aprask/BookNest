package gamescout.api.dashboard.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

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

}
