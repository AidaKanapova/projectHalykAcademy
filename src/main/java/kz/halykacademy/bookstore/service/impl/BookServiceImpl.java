package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.SaveBookDTO;
import kz.halykacademy.bookstore.dto.UpdateBookDTO;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.mapper.BookMapper;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.repository.PublisherRepository;
import kz.halykacademy.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {



    private final BookRepository bookRepository;
    private  final PublisherRepository publisherRepository;
    private  final GenreRepository genreRepository;
    private final BookMapper bookMapper;


    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @Override
    public BookDTO getBookById(long bookId) throws Throwable {
        return bookRepository.findById(bookId)
                .map(bookMapper::toDTO)
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("Book %s not found".formatted(bookId)));
    }
    @Override
    public BookDTO createBook(SaveBookDTO book) throws  Throwable {
        Publisher publisher = publisherRepository.findById(book.getPublisherId())
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("Cannot persist book %s".formatted(book.getBookId())));

        Books saved = bookRepository.save(
                new Books(
                        book.getBookId(),
                        book.getTitle(),
                        null,
                        book.getPrice(),
                        null,
                        publisher,
                        book.getPageCount(),
                        book.getReleaseYear(),
                        false
                )
        );
        return  bookMapper.toDTO(saved);
    }

    @Override
    public BookDTO updateBook(UpdateBookDTO book, long id) throws Throwable {


       /*Publisher publisher = publisherRepository.findById(book.getPublisherId())
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("Cannot persist book %s".formatted(id)));*/


        Books books = bookRepository.findById(id).get();

        Books saveBook = bookRepository.save(
                new Books(
                        books.getBookId(),
                        book.getTitle(),
                        books.getGenres(),
                        book.getPrice(),
                        books.getAuthors(),
                        books.getPublisher(),
                        book.getPage_count(),
                        book.getRelease_year(),
                        books.isDeleted()
                )
        );


      return  bookMapper.toDTO(saveBook);

    }


    @Override
    public void deleteBook(long bookId) throws Exception {
        bookRepository.deleteById(bookId);
    }


    @Override
    public List<BookDTO> findByTitle(String title) {

        return bookRepository.findByTitle(title).stream().map(bookMapper::toDTO).toList();
    }

    @Override
    public List<BookDTO> findByGenreList(String genreList) {
        /*Genre genreOnRequest = genreRepository.findByName(genreList);
        List<Books> bookList = bookRepository.getBookList(genreOnRequest.getGenre_id());
        return bookList.stream().map(bookMapper::toDTO).toList();*/

/*
        return  bookRepository.genreList(genreList).stream().map(bookMapper::toDTO).toList();
*/
        return bookRepository.genreList(genreList).stream().map(bookMapper::toDTO).toList();



    }
}
