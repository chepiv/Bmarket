package com.zpi.bmarket.bmarket.DTO;

import com.zpi.bmarket.bmarket.domain.*;
import com.zpi.bmarket.bmarket.repositories.StatusRepository;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import sun.util.calendar.BaseCalendar;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AddOfferDTO {

    @NotNull
    @NotEmpty
    private Date publishDate;

    private Date boughtDate;

    @NotNull
    @NotEmpty
    private OfferType offerType;

    @NotNull
    @NotEmpty
    private Status status;

    @NotNull
    @NotEmpty
    private String title;

    private String description;

    private int price;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private List<Book> books;



    public Offer getOffer(User user){

        Offer offer = new Offer();

        offer.setPublishDate(new Date());
        offer.setOfferType(offerType);
        offer.setStatus(status);  //TODO: jakie statusy - ustawić że oferta jest aktywna
        offer.setTitle(title);
        offer.setDescription(description);
        offer.setPrice(price);
        offer.setCity(city);

        offer.setBooks(books); //TODO: dodać jakoś książki
        user.getOffers().add(offer);
        for (Book book : books) {
            book.setOffer(offer);
        }

        return offer;
    }

}
