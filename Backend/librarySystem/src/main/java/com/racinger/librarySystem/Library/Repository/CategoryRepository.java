package com.racinger.librarySystem.Library.Repository;

import com.racinger.librarySystem.Library.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT COUNT(c) FROM Category c")
    Long countTotalCategories();

    @Query("SELECT COUNT(b) > 0 FROM Book b WHERE b.category.id = :categoryId")
    boolean hasAssociatedBooks(@Param("categoryId") Long categoryId);
}