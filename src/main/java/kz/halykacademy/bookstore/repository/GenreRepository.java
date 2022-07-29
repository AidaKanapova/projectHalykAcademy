package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
