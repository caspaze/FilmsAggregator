package com.vsu.Repository;

import com.vsu.Models.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {
    Page<Film> findFilmsByNameContainingIgnoreCase(String name, Pageable pageable);
    @Query("from Film order by date desc")
    List<Film> findLastFilms();

}
