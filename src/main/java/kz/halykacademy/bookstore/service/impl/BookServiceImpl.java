package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Books> getAllBooks() {
        return (List<Books>) bookRepository.findAll();
    }

    @Override
    public Books getBookById(int bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public Books createBook(Books book) {
        return null;
    }

    @Override
    public Books updateBooks(Books book) {
        return bookRepository.save(book);
    }

    @Override
    public Books deleteBook(int bookId) throws Exception {
        Books deleteBook = null;
        try {
            deleteBook = bookRepository.findById(bookId).orElse(null);
            if (deleteBook == null) {
                throw new Exception("book not available");
            } else {
                bookRepository.deleteById(bookId);
            }
        } catch (Exception ex) {
            throw ex;
        }


        return deleteBook;
    }
}
