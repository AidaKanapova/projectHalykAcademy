package kz.halykacademy.bookstore.repository;


import kz.halykacademy.bookstore.dto.AuthorGenreDTO;
import kz.halykacademy.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
    List<AuthorGenreDTO> findGenreList(Long id);
*/




}
