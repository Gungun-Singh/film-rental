package com.filmrental.controller;

import com.filmrental.dto.request.CreateFilmRequest;
import com.filmrental.dto.response.FilmResponse;
import com.filmrental.service.FilmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/films") @RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    // GET /films/{film_id}
    @GetMapping("/{filmId}")
    public ResponseEntity<FilmResponse> getFilmById(@PathVariable Integer filmId) {
        return ResponseEntity.ok(filmService.getFilmById(filmId));
    }
}