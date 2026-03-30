package com.filmrental.mapper;
import com.filmrental.entity.Film;
import com.filmrental.dto.response.FilmResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class FilmMapper {
    public FilmResponse toResponse(Film film) {
        List<String> categories = film.getCategories() == null ? List.of() :
                film.getCategories().stream().map(c -> c.getName()).collect(Collectors.toList());
        List<String> actors = film.getActors() == null ? List.of() :
                film.getActors().stream().map(a -> a.getFirstName() + " " + a.getLastName()).collect(Collectors.toList());
        return FilmResponse.builder()
                .filmId(film.getFilmId()).title(film.getTitle()).description(film.getDescription())
                .releaseYear(film.getReleaseYear())
                .language(film.getLanguage() != null ? film.getLanguage().getName() : null)
                .rentalDuration(film.getRentalDuration()).rentalRate(film.getRentalRate())
                .length(film.getLength()).replacementCost(film.getReplacementCost())
                .rating(film.getRating()).categories(categories).actors(actors).build();
    }
}
