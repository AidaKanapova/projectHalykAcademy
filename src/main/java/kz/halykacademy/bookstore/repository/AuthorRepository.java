package kz.halykacademy.bookstore.repository;


import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    boolean existsByAuthorId(Long id);

    @Query(value = "SELECT  u FROM Author u WHERE lower(u.fullName) LIKE CONCAT('%',lower(:name), '%') ")
    List<Author> findByName(String name);

    @Query(value = "select  a from Author a where a.authorId in :listId")
    Set<Author> getAuthorsByListId(List<Long> listId);

    @Query(value = """ 
                    select distinct c.genre_name
                                from public.book_author a\s
                                join public.book_genre g\s
                                on a.book_id = g.book_id\s
                    			join genre c\s
                    			on g.genre_id = c.genre_id
                                where a.author_id = :authorId
                    """, nativeQuery = true)
    List<String> genreList(long authorId);

    @Query(value = """
 select distinct f.*
                                from book_author a
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
