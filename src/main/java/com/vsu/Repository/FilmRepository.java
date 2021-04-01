package com.vsu.Repository;

import com.vsu.Models.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface FilmRepository extends JpaRepository<Film,Long> {
    Page<Film> findFilmsByNameContaining(String name, Pageable pageable);
    //List<Film> findFilmsByNameContaining(String name);
    @Query("from Film order by date desc")
    List<Film> findLastFilms();

}
