package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Books;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Books, Integer> {


}
