package com.zpi.bmarket.bmarket.DTO;

import com.zpi.bmarket.bmarket.domain.BookCondition;
import com.zpi.bmarket.bmarket.domain.OfferType;
import com.zpi.bmarket.bmarket.domain.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchOfferDTO {

    List<BookCondition> conditions;

    List<OfferType> offerTypes;

    List<Status> statuses;

    OfferType type;


}
