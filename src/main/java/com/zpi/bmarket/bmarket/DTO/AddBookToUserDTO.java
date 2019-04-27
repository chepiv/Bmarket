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
public class AddBookToUserDTO {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String authors;

    @NotNull
    @NotEmpty
    private BookCondition bookCondition;

    @NotNull
    @NotEmpty
    private String publisher;

    @NotNull
    @NotEmpty
    private String isbn;

    @NotNull
    @NotEmpty
    private Category category;


    //TODO: add author in future

    public Book getBook(User user){

        Book book = new Book();

        book.setUser(user);
        book.setTitle(this.title);
        book.setAuthors(this.authors);
        book.setBookCondition(this.bookCondition);
        book.setPublisher(this.publisher);
        book.setIsbn(this.isbn);
        book.setCategory(this.category);

        return book;
    }
}
