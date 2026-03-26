package vod.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Author;
import vod.model.Book;
import vod.model.Library;
import vod.repository.BookDao;

import java.util.List;

@Repository
public class JpaBookDao implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findAll() {
        return entityManager
                .createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    @Override
    public Book findById(int id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        return entityManager
                .createQuery(
                        "select b from Book b where b.author.id = :authorId",
                        Book.class
                )
                .setParameter("authorId", author.getId())
                .getResultList();
    }

    @Override
    public List<Book> findByLibrary(Library library) {
        return entityManager
                .createQuery(
                        "select b from Book b join b.libraries l where l.id = :libraryId",
                        Book.class
                )
                .setParameter("libraryId", library.getId())
                .getResultList();
    }

    @Override
    @Transactional
    public Book add(Book book) {
        entityManager.persist(book);
        return book;
    }
}