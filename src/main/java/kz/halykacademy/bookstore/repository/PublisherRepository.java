package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Query(value = "SELECT  u FROM Publisher u WHERE lower(u.name)  LIKE  CONCAT('%',lower(:name), '%')  ")
    List<Publisher> findByName(String name);

    boolean existsById(Long id);

}
