package vod.service.impl;

import org.springframework.stereotype.Service;
import vod.model.Library;
import vod.model.Book;
import vod.repository.LibraryDao;
import vod.repository.BookDao;
import vod.service.LibraryService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class LibraryServiceBean implements LibraryService {

    private static final Logger log = Logger.getLogger(LibraryService.class.getName());

    private LibraryDao libraryDao;
    private BookDao bookDao;

    public LibraryServiceBean(LibraryDao libraryDao, BookDao bookDao) {
        log.info("creating library service bean");
        this.libraryDao = libraryDao;
        this.bookDao = bookDao;
    }

    @Override
    public Library getLibraryById(int id) {
        log.info("searching library by id " + id);
        return libraryDao.findById(id);
    }

    @Override
    public List<Book> getBooksInLibrary(Library c) {
        log.info("searching books in library " + c.getId());
        return bookDao.findByLibrary(c);
    }

    @Override
    public List<Library> getAllLibraries() {
        log.info("searching all libraries");
        return libraryDao.findAll();
    }

    @Override
    public List<Library> getLibrariesByBook(Book m) {
        log.info("searching libraries by book " + m.getId());
        return libraryDao.findByBook(m);
    }

    @Override
    public Library add(Library c) {
        log.info("about to add library " + c);
        return libraryDao.add(c);
    }
}