package com.racinger.librarySystem.Library.Repository;

import com.racinger.librarySystem.Library.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

    @Query("SELECT a FROM Author a WHERE a.firstName LIKE %:name% OR a.lastName LIKE %:name%")
    List<Author> searchAuthors(@Param("name") String name);

    @Query("SELECT a FROM Author a WHERE a.nationality = :nationality")
    List<Author> findByNationality(@Param("nationality") String nationality);

    @Query("SELECT COUNT(a) FROM Author a")
    Long countTotalAuthors();

    @Query("SELECT COUNT(ba) > 0 FROM BookAuthor ba WHERE ba.author.id = :authorId")
    boolean hasAssociatedBooks(@Param("authorId") Long authorId);
}