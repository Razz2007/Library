package com.racinger.librarySystem.Library.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String title;
    private String description;
    private Integer publicationYear;
    private String isbn;
    private Integer totalCopies;
    private Integer availableCopies;
    private Long categoryId;
    private String categoryName;
    private List<AuthorDto> authors;
}