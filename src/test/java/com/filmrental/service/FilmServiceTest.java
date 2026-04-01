package com.filmrental.service;

import com.filmrental.dto.response.FilmResponse;
import com.filmrental.entity.Film;
import com.filmrental.exception.ResourceNotFoundException;
import com.filmrental.mapper.FilmMapper;
import com.filmrental.repository.FilmRepository;
import com.filmrental.service.impl.FilmServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private FilmMapper filmMapper;

    @InjectMocks
    private FilmServiceImpl filmService;

    // -- test cases for getFilmById --

    @Test
    void getFilmById_success() {
        Film film = new Film();
        film.setFilmId(1);

        when(filmRepository.findById(1)).thenReturn(Optional.of(film));
        when(filmMapper.toResponse(film)).thenReturn(FilmResponse.builder().filmId(1).build());

        FilmResponse result = filmService.getFilmById(1);

        assertEquals(1, result.getFilmId());
    }

    @Test
    void getFilmById_notFound() {
        when(filmRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> filmService.getFilmById(99));
    }

    @Test
    void getFilmById_edge_verifyAndMessage() {
        when(filmRepository.findById(99)).thenReturn(Optional.empty());

        Exception ex = assertThrows(ResourceNotFoundException.class,
                () -> filmService.getFilmById(99));

        assertTrue(ex.getMessage().contains("Film not found"));
        verify(filmRepository).findById(99);
    }

}