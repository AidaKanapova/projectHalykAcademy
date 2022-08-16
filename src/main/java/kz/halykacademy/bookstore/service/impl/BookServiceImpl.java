package kz.halykacademy.bookstore.service.impl;
import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.BookGenreDTO;
import kz.halykacademy.bookstore.dto.SaveBookDTO;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.errors.InvalidValueException;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.mapper.BookMapper;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.repository.PublisherRepository;
import kz.halykacademy.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @Override
    public BookDTO getBookById(Long bookId) throws Throwable {
        return bookRepository.findById(bookId)
                .map(bookMapper::toDTO)
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("Book with id %s not found".formatted(bookId)));
    }

    @Override
    public BookDTO addBook(SaveBookDTO book) throws Throwable {
        Publisher publisher = publisherRepository.findById(book.getPublisherId())
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("publisher with id %s not founded".formatted(book.getPublisherId())));


        Set<Author> authorsList = authorRepository.getAuthorsByListId(book.getAuthorList());
        Set<Genre> genreList = genreRepository.getGenresByListId(book.getGenreList());

        if (authorsList.isEmpty()) {
            throw  new InvalidValueException("incorrect list of authors");
        }
        if (genreList.isEmpty()) {
            throw  new InvalidValueException("incorrect list of genres");
        }

        Books saveBook = bookRepository.save(
                new Books(
                        null,
                        book.getTitle(),
                        genreList,
                        book.getPrice(),
                        authorsList,
                        publisher,
                        book.getPageCount(),
                        book.getReleaseYear(),
                        false
                )
        );
        return bookMapper.toDTO(saveBook);
    }

    @Override
    public BookDTO updateBook(SaveBookDTO book) throws Throwable {

        Publisher publisher = publisherRepository.findById(book.getPublisherId())
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("publisher with id %s not found".formatted(book.getPublisherId())));

        Books givenBooks = bookRepository.findById(book.getBookId()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("book with id %s not found".formatted(book.getBookId())));
        Set<Genre> genreList = genreRepository.getGenresByListId(book.getGenreList());
        Set<Author> authorsList = authorRepository.getAuthorsByListId(book.getAuthorList());

        if (authorsList.isEmpty()) {
            throw  new InvalidValueException("incorrect list of authors");
        }
        if (genreList.isEmpty()) {
            throw  new InvalidValueException("incorrect list of genres");
        }

        Books updateBook = bookRepository.save(
                new Books(
                        book.getBookId(),
                        book.getTitle(),
                        genreList,
                        book.getPrice(),
                        authorsList,
                        publisher,
                        book.getPageCount(),
                        book.getReleaseYear(),
                        givenBooks.isDeleted()
                )
        );
        return bookMapper.toDTO(updateBook);
    }


    @Override
    public void deleteBook(Long bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
        } else throw new ResourceNotFoundeException("book with id %s not founded");
    }


    @Override
    public List<BookDTO> findByTitle(String title) {
        return bookRepository.findByTitle(title).stream().map(bookMapper::toDTO).toList();
    }

    @Override
    public List<BookGenreDTO> findByGenreList(List<String> genreNameList) {
        return bookRepository.genreList(genreNameList).stream().map(bookMapper::toGenreDTO).toList();
    }
}
