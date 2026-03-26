package vod.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Book;
import vod.model.Library;
import vod.repository.LibraryDao;

import java.util.List;

@Repository
public class JpaLibraryDao implements LibraryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Library> findAll() {
        return entityManager
                .createQuery("select l from Library l", Library.class)
                .getResultList();
    }

    @Override
    public Library findById(int id) {
        return entityManager.find(Library.class, id);
    }

    @Override
    public List<Library> findByBook(Book book) {
        return entityManager
                .createQuery(
                        "select l from Library l join l.books b where b.id = :bookId",
                        Library.class
                )
                .setParameter("bookId", book.getId())
                .getResultList();
    }

    @Override
    @Transactional
    public Library add(Library library) {
        entityManager.persist(library);
        return library;
    }
}