package kz.halykacademy.bookstore.repository;


import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query(value = "SELECT  u FROM Author u WHERE lower(u.full_name)  LIKE %?1% ")
    List<Author> findByName(String name);

    @Query(value = " select distinct g.genre_id \n" +
            "from public.author_book a \n" +
            "join public.book_genre g \n" +
            "on a.book_id = g.book_id \n" +
            "where a.author_id = :authorId ",nativeQuery = true)
    List<Long> genreList(long authorId);

    @Query(value = " select distinct g" +
            "from public.author_book a \n" +
            "join public.book_genre g \n" +
            "on a.book_id = g.book_id \n" +
            "where a.author_id = :authorId ",nativeQuery = true)
    List<Genre> genres (long authorId);
}
