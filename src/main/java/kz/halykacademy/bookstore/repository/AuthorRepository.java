package kz.halykacademy.bookstore.repository;


import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    @Query(value = "SELECT  u FROM Author u WHERE u.fullName  LIKE %?1% ")
    List<Author> findByName(String name);

}
