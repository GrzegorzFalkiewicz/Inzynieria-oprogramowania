package vod.repository.mem;

import org.springframework.stereotype.Repository;
import vod.model.Author;
import vod.repository.AuthorDao;

import java.util.List;

@Repository
public class MemAuthorDao implements AuthorDao {

    @Override
    public List<Author> findAll() {
        return SampleData.authors;
    }

    @Override
    public Author findById(int id) {
        return SampleData.authors.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Author add(Author d) {
        int max = SampleData.authors.stream().max((d1, d2) -> d1.getId() - d2.getId()).get().getId();
        d.setId(++max);
        SampleData.authors.add(d);
        return d;
    }
}