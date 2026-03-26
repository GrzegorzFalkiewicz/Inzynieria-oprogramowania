package vod.repository.data;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Book;
import vod.model.Library;
import vod.repository.LibraryDao;

import java.util.List;

@Primary
@Repository
public class DataLibraryDao implements LibraryDao {

    private final DataLibraryRepository dataLibraryRepository;

    public DataLibraryDao(DataLibraryRepository dataLibraryRepository) {
        this.dataLibraryRepository = dataLibraryRepository;
    }

    @Override
    public List<Library> findAll() {
        return dataLibraryRepository.findAll();
    }

    @Override
    public Library findById(int id) {
        return dataLibraryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Library> findByBook(Book book) {
        return dataLibraryRepository.findAllByBookId(book.getId());
    }

    @Override
    public Library add(Library library) {
        return dataLibraryRepository.save(library);
    }
}