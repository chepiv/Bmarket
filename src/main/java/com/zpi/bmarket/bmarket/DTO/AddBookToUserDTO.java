package com.zpi.bmarket.bmarket.DTO;


import com.zpi.bmarket.bmarket.domain.Book;
import com.zpi.bmarket.bmarket.domain.BookCondition;
import com.zpi.bmarket.bmarket.domain.Category;
import com.zpi.bmarket.bmarket.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddBookToUserDTO {

    @NotNull
    @NotEmpty
    private String title;

    private String authors;

    private BookCondition bookCondition;

    private String publisher;

    private String isbn;

    private Category category;

    private String photoUrl;

    private MultipartFile image;

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
