package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsById(Long id);



}
