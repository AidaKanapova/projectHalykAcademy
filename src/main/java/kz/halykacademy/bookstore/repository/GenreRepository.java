package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query(value = "SELECT  u FROM Genre u WHERE lower(u.genre_name)  LIKE %?1% ")
    Genre findByName(String name);

}
