package kz.halykacademy.bookstore.repository;


import kz.halykacademy.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

   /* EntityManagerFactory emf = Persistence.
            createEntityManagerFactory( "Eclipselink_JPA" );
    EntityManager em = emf.
            createEntityManager();

    List<Book_genre> genre = em.createQuery("select g from Author_book a join Book_genre g on a.book_id = g.book_id where a.author_id = ?1").getResultList();
*/
    @Query(value = "SELECT  u FROM Author u WHERE u.full_name  LIKE %?1% ")
    List<Author> findByName(String name);




/*
    @Query(value = "select Genre .genre_name from B a join Genre .books g on a.bookId = g.bookId where Author .authorId = :authorId", nativeQuery = true)
*/

    @Query(value = " select distinct g.genre_id \n" +
            "from public.author_book a \n" +
            "join public.book_genre g \n" +
            "on a.book_id = g.book_id \n" +
            "where a.author_id = :authorId ",nativeQuery = true)
    List<Long> genreList(long authorId);

/*
    List<AuthorGenreDTO> findGenreList(Long id);
*/




}
