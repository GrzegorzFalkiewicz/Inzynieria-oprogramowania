package vod.repository.mem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Book;
import vod.model.Library;
import vod.repository.LibraryDao;

import java.util.List;
import java.util.stream.Collectors;

//@Primary
@Repository
public class MemLibraryDao implements LibraryDao {

    @Override
    public List<Library> findAll() {
        return SampleData.libraries;
    }

    @Override
    public Library findById(int id) {
        return SampleData.libraries.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Library> findByBook(Book m) {
        return SampleData.libraries.stream().filter(c -> c.getBooks().contains(m)).collect(Collectors.toList());
    }

    @Override
    public Library add(Library c) {
        int max = SampleData.libraries.stream()
                .max((c1, c2) -> c1.getId() - c2.getId())
                .get()
                .getId();

        c.setId(++max);
        SampleData.libraries.add(c);
        return c;
    }
}