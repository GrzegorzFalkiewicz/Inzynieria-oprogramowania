package vod.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vod.model.Author;
import vod.model.Book;
import vod.model.Library;
import vod.repository.AuthorDao;
import vod.repository.BookDao;
import vod.repository.LibraryDao;
import vod.service.BookService;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class BookServiceBean implements BookService {

    private static final Logger log = Logger.getLogger(BookService.class.getName());

    private final AuthorDao authorDao;
    private final LibraryDao libraryDao;
    private final BookDao bookDao;

    @Override
    public List<Book> getAllBooks() {
        log.info("searching all books...");
        return bookDao.findAll();
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        log.info("searching books by author " + author.getId());
        return bookDao.findByAuthor(author);
    }

    public List<Book> getBooksInLibrary(Library library) {
        log.info("searching books in library " + library.getId());
        return bookDao.findByLibrary(library);
    }

    @Override
    public Book getBookById(int id) {
        log.info("searching book by id " + id);
        return bookDao.findById(id);
    }

    public List<Library> getAllLibraries() {
        log.info("searching all libraries...");
        return libraryDao.findAll();
    }

    public List<Library> getLibrariesByBook(Book book) {
        log.info("searching libraries by book " + book.getId());
        return libraryDao.findByBook(book);
    }

    public Library getLibraryById(int id) {
        log.info("searching library by id " + id);
        return libraryDao.findById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        log.info("searching all authors");
        return authorDao.findAll();
    }

    @Override
    public Author getAuthorById(int id) {
        log.info("searching author by id " + id);
        return authorDao.findById(id);
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        log.info("about to add book " + book);

        Book addedBook = bookDao.add(book);

        if ("blad".equalsIgnoreCase(book.getTitle())) {
            throw new IllegalArgumentException("testowy wyjatek przy dodawaniu ksiazki");
        }

        return addedBook;
    }

    @Override
    public Author addAuthor(Author author) {
        log.info("about to add author " + author);
        return authorDao.add(author);
    }
}