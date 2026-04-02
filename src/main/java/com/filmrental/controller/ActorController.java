package com.filmrental.controller;

import com.filmrental.dto.response.FilmResponse;
import com.filmrental.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/actors")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;

    // GET /actors/{actor_id}/films
    @GetMapping("/{actorId}/films")
    public ResponseEntity<List<FilmResponse>> getFilmsByActor(@PathVariable Integer actorId) {
        return ResponseEntity.ok(actorService.getFilmsByActorId(actorId));
    }
}
