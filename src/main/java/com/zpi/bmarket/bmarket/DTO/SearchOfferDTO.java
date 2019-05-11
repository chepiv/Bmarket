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

    private List<BookCondition> conditions;

    private List<OfferType> offerTypes;

    private List<Status> statuses;

    private int priceMin;
    private int priceMax;

    public void removeNulls(){
        conditions.removeIf(x->x==null || x.getId()==null || x.getValue()==null);
        offerTypes.removeIf(x->x==null || x.getId()==null || x.getType()==null);
        statuses.removeIf(x->x==null || x.getId()==null || x.getStatus()==null);
    }

}
