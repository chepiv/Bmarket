package com.zpi.bmarket.bmarket.DTO;


import com.zpi.bmarket.bmarket.domain.Book;
import com.zpi.bmarket.bmarket.domain.Category;
import com.zpi.bmarket.bmarket.domain.Condition;
import com.zpi.bmarket.bmarket.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Getter
@Setter
public class AddBookToUserDTO {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private Condition condition;

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

    public Book getBook(Optional<User> user){

        Book book = new Book();

        book.setUser(user);
        book.setTitle(this.title);
        book.setCondition(this.condition);
        book.setPublisher(this.publisher);
        book.setIsbn(this.isbn);
        book.setCategory(this.category);

        return book;
    }
}
