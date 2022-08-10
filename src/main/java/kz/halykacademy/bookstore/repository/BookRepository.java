package kz.halykacademy.bookstore.repository;


import kz.halykacademy.bookstore.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    @Query(value = "SELECT  u FROM Books u WHERE lower(u.title)   LIKE  %?1% ")
    List<Books> findByTitle(String title);

    @Query(value = """
select distinct b
                                from book_genre g
                                join book b
                                on g.book_id = b.book_id
                                where g.genre_id = :genreList
""", nativeQuery = true)
    List<Books> getBookList(long genreList);

    @Query(value = "select c.title\n" +
            "from book_genre g\n" +
            "join genre b\n" +
            "on g.genre_id = b.genre_id\n" +
            "join book c\n" +
            "on g.book_id = c.book_id\n" +
            "where b.genre_name = :genreName", nativeQuery = true)
    List<Books> genreList(String genreName);


}
