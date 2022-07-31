package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.dto.AuthorDTO;
import kz.halykacademy.bookstore.dto.AuthorGenreDTO;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Query(value = "SELECT  u FROM Publisher u WHERE u.name  LIKE %?1% ")
    List<Publisher> findByName(String name);

    List<AuthorGenreDTO> getGenreList(AuthorDTO authorDTO) ;

}
