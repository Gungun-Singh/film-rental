package com.filmrental.repository;

import com.filmrental.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    List<Film> findByCategories_CategoryId(Integer categoryId);
    List<Film> findByActors_ActorId(Integer actorId);
}
