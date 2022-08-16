package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    @Query(value = "SELECT  u FROM Books u WHERE lower(u.title)   LIKE  CONCAT('%',lower(:title), '%') ")
    List<Books> findByTitle(String title);




    @Query(value = """
select distinct c.*
            from book_genre g
            join genre b
            on g.genre_id = b.genre_id
            join book c
            on g.book_id = c.book_id
            where b.genre_name in (:genreName)
""", nativeQuery = true)
    List<Books> genreList(@Param("genreName") List<String> genreName);

    @Query(value = """
                    select b.*\s
                    from book b
                    where book_id in :listId
""", nativeQuery = true)
    List<Books> getBooksByListId(List<Long> listId);

    boolean existsById(Long id);



}
