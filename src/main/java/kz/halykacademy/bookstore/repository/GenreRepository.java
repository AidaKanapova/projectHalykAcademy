package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query(value = "SELECT  u FROM Genre u WHERE lower(u.genreName)  LIKE  CONCAT('%',lower(:name), '%')  ")
    Genre findByName(String name);


    @Query(value = "select  g from Genre g where g.id in :listId")
    Set<Genre> getGenresByListId(List<Long> listId);


    boolean existsById(Long id);

}
