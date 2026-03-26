package vod.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Author;
import vod.repository.AuthorDao;

import java.util.List;

@Repository
public class JpaAuthorDao implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Author> findAll() {
        return entityManager
                .createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    @Override
    public Author findById(int id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    @Transactional
    public Author add(Author author) {
        entityManager.persist(author);
        return author;
    }
}