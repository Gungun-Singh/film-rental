package com.filmrental.dto.response;
import lombok.Builder; import lombok.Data;
import java.math.BigDecimal; import java.util.List;
@Data @Builder
public class FilmResponse {
    private Integer filmId;
    private String title;
    private String description;
    private Integer releaseYear;
    private String language;
    private Integer rentalDuration;
    private BigDecimal rentalRate;
    private Integer length;
    private BigDecimal replacementCost;
    private String rating;
    private List<String> categories;
    private List<String> actors;
}
