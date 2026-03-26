package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.Author;

public interface DataAuthorRepository extends JpaRepository<Author, Integer> {
}