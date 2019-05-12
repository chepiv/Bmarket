package com.zpi.bmarket.bmarket.DTO;


import com.zpi.bmarket.bmarket.domain.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class BookToUserDTO {

    @NotNull
    @NotEmpty
    private String title;

    private String authors;

    private BookCondition bookCondition;

    private String publisher;

    private String isbn;

    private Category category;

    private String photoUrl;

    private String description;


    public Book getBook(User user){

        Book book = new Book();

        book.setUser(user);
        book.setTitle(this.title);
        book.setAuthors(this.authors);
        book.setBookCondition(this.bookCondition);
        book.setPublisher(this.publisher);
        book.setIsbn(this.isbn);
        book.setCategory(this.category);
        book.setPhotoUrl(this.photoUrl);
        book.setDescription(this.description);

        return book;
    }
}
