package vod.repository.data;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Author;
import vod.repository.AuthorDao;

import java.util.List;

@Primary
@Repository
public class DataAuthorDao implements AuthorDao {

    private final DataAuthorRepository dataAuthorRepository;

    public DataAuthorDao(DataAuthorRepository dataAuthorRepository) {
        this.dataAuthorRepository = dataAuthorRepository;
    }

    @Override
    public List<Author> findAll() {
        return dataAuthorRepository.findAll();
    }

    @Override
    public Author findById(int id) {
        return dataAuthorRepository.findById(id).orElse(null);
    }

    @Override
    public Author add(Author author) {
        return dataAuthorRepository.save(author);
    }
}