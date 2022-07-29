package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    @Query(value = "SELECT  u FROM Books u WHERE u.title  LIKE %?1% ")
    List<Books> findByTitle(String title);



}
