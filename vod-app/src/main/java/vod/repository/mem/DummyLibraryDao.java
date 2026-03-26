package vod.repository.mem;

import org.springframework.stereotype.Component;
import vod.model.Book;
import vod.model.Library;
import vod.repository.LibraryDao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class DummyLibraryDao implements LibraryDao {

    private static final Logger log = Logger.getLogger(DummyLibraryDao.class.getName());

    @Override
    public List<Library> findAll() {
        log.info("dummy library dao - returning empty library list");
        return new ArrayList<>();
    }

    @Override
    public Library findById(int id) {
        log.info("dummy library dao - searching library by id " + id);
        return null;
    }

    @Override
    public List<Library> findByBook(Book m) {
        log.info("dummy library dao - searching libraries by book " + m.getId());
        return new ArrayList<>();
    }

    @Override

    public Library add(Library c) {
        log.info("dummy library dao - add not supported");
        return c;
    }
}