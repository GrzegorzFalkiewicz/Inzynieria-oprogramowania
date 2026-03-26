package vod.repository.data;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vod.model.Author;
import vod.model.Book;
import vod.model.Library;
import vod.repository.BookDao;

import java.util.List;

@Primary
@Repository
public class DataBookDao implements BookDao {

    private final DataBookRepository dataBookRepository;

    public DataBookDao(DataBookRepository dataBookRepository) {
        this.dataBookRepository = dataBookRepository;
    }

    @Override
    public List<Book> findAll() {
        return dataBookRepository.findAll();
    }

    @Override
    public Book findById(int id) {
        return dataBookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        return dataBookRepository.findAllByAuthor_Id(author.getId());
    }

    @Override
    public List<Book> findByLibrary(Library library) {
        return dataBookRepository.findAllByLibraries_Id(library.getId());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Book add(Book book) {
        return dataBookRepository.save(book);
    }
}