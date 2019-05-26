package com.zpi.bmarket.bmarket.controllers;

import com.zpi.bmarket.bmarket.DTO.AddBookToUserDTO;
import com.zpi.bmarket.bmarket.PostStatus;
import com.zpi.bmarket.bmarket.domain.Book;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.BookRepository;
import com.zpi.bmarket.bmarket.repositories.CategoryRepository;
import com.zpi.bmarket.bmarket.repositories.ConditionRepository;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import com.zpi.bmarket.bmarket.services.ContentPathAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class UserBookController {
    private static Logger logger = Logger.getLogger(UserBookController.class.getName());
    private static final String UPLOADED_FOLDER = ContentPathAccessor.getContentPath();

    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ConditionRepository conditionRepository;


    @GetMapping(value = "/addBook")
    public String getAddUserBook(Model model) {

        AddBookToUserDTO bookDTO = new AddBookToUserDTO();

        model.addAttribute("bookDTO", bookDTO);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("conditions", conditionRepository.findAll());

        return "addBookView";
    }

    @RequestMapping(value = "/postAddUserBook", method = RequestMethod.POST)
    public String postAddUserBook(@ModelAttribute AddBookToUserDTO bookDTO, Model model, HttpSession session) {

        PostStatus status;
        Long id = (Long) session.getAttribute("userId");
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id: " + id));

        Book book = bookDTO.getBook(user);

        try {
            String title = bookDTO.getTitle();
            saveUploadedFile(bookDTO.getImage(), title);
            book.setPhotoUrl(title + bookDTO.getImage().getOriginalFilename());
            bookRepository.save(book);
            model.addAttribute("book", book);
            status = PostStatus.SUCCESS;
        } catch (Exception e) {
            status = PostStatus.DATABASE_ERROR;
            logger.log(Level.WARNING, "Database Error", e);
        }


        model.addAttribute("status", status);
        return "postAddUserBookView";
    }

    private void saveUploadedFile(MultipartFile file, String title) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + title + file.getOriginalFilename());
            Files.write(path, bytes);
        }
    }
}
