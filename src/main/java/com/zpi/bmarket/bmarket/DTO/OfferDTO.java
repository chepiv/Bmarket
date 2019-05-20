package com.zpi.bmarket.bmarket.DTO;

import com.zpi.bmarket.bmarket.domain.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class OfferDTO {

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



    public Offer createOffer(User user){

        Offer offer = new Offer();
        
        offer.setOfferType(offerType);
        offer.setTitle(title);
        offer.setDescription(description);
        offer.setPrice(price);
        offer.setCity(city);
        offer.setStatus(status);
        offer.setPublishDate(publishDate);
        user.getOffers().add(offer);
        for (Book book : books) {
            book.setOffer(offer);
        }
        offer.setBooks(books);

        return offer;
    }


    public void setStatus(Optional<Status> offerStatus) {
    }
}
