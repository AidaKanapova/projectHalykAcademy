package kz.halykacademy.bookstore.provider;

import kz.halykacademy.bookstore.dto.AuthorDTO;

import java.util.List;

public interface AuthorProvider {
    List<AuthorDTO> getAll();
}
