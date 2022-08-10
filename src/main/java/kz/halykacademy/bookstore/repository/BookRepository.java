package kz.halykacademy.bookstore.repository;


import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.BookNameDTO;
import kz.halykacademy.bookstore.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    @Query(value = "SELECT  u FROM Books u WHERE lower(u.title)   LIKE  %?1% ")
    List<Books> findByTitle(String title);



    @Query(value = "select distinct c.*\n" +
            "from book_genre g\n" +
            "join genre b\n" +
            "on g.genre_id = b.genre_id\n" +
            "join book c\n" +
            "on g.book_id = c.book_id\n" +
            "where b.genre_name in (:genreName)", nativeQuery = true)
    List<Books> genreList(@Param("genreName") List<String> genreName);



}
