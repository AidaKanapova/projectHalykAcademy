package kz.halykacademy.bookstore.repository;


import kz.halykacademy.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query(value = "SELECT  u FROM Author u WHERE lower(u.full_name)  LIKE %?1% ")
    List<Author> findByName(String name);

    @Query(value = """ 
                    select distinct c.genre_name
                                from public.author_book a\s
                                join public.book_genre g\s
                                on a.book_id = g.book_id\s
                    			join genre c\s
                    			on g.genre_id = c.genre_id
                                where a.author_id = :authorId
                    """, nativeQuery = true)
    List<String> genreList(long authorId);

    @Query(value = """
 select distinct f.*
                                from author_book a
                                join book_genre g
                                on a.book_id = g.book_id
                    			join genre c
                    			on g.genre_id = c.genre_id
								join author f
								on f.author_id = a.author_id
                                where c.genre_name in (:genreList)
""", nativeQuery = true)
    List<Author> getAuthorByGenreList(@Param("genreList") List<String> genreList);




}
