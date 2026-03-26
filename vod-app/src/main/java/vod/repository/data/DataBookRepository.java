package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.Book;

import java.util.List;

public interface DataBookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByAuthor_Id(int authorId);

    List<Book> findAllByLibraries_Id(int libraryId);
}