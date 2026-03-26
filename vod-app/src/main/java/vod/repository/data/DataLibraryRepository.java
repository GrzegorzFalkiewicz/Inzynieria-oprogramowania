package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vod.model.Library;

import java.util.List;

public interface DataLibraryRepository extends JpaRepository<Library, Integer> {

    @Query("select l from Library l join l.books b where b.id = :bookId")
    List<Library> findAllByBookId(@Param("bookId") int bookId);
}