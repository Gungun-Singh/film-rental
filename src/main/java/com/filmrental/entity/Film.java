package com.filmrental.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="film")
@Data
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue
            (strategy=GenerationType.IDENTITY)

    @Column(name="film_id") private Integer filmId;
    @Column(name="title",nullable=false) private String title;
    @Column(name="description",columnDefinition="TEXT") private String description;
    @Column(name="release_year") private Integer releaseYear;

    @ManyToOne
            (fetch=FetchType.LAZY)
    @JoinColumn
            (name="language_id",nullable=false) private Language language;

    @Column(name="rental_duration",nullable=false) private Integer rentalDuration;
    @Column(name="rental_rate",nullable=false) private BigDecimal rentalRate;
    @Column(name="length") private Integer length;
    @Column(name="replacement_cost",nullable=false) private BigDecimal replacementCost;
    @Column(name="rating") private String rating;
    @Column(name="last_update",insertable=false,updatable=false) private LocalDateTime lastUpdate;

    @ManyToMany
            (fetch=FetchType.LAZY)

    @JoinTable
            (name="film_category",joinColumns=@JoinColumn(name="film_id"),inverseJoinColumns=@JoinColumn(name="category_id"))

    @ToString.Exclude
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(fetch=FetchType.LAZY)

    @JoinTable
            (name="film_actor",joinColumns=@JoinColumn(name="film_id"),inverseJoinColumns=@JoinColumn(name="actor_id"))

    @ToString.Exclude
    private List<Actor> actors = new ArrayList<>();
}
