package com.zpi.bmarket.bmarket.DTO;

import com.zpi.bmarket.bmarket.domain.BookCondition;
import com.zpi.bmarket.bmarket.domain.Category;
import com.zpi.bmarket.bmarket.domain.OfferType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchOfferDTO {

    //offer types
    private boolean isSale;
    private boolean isExchange;
    private boolean isFree;

    private OfferType typeSale;
    private OfferType typeExchange;
    private OfferType typeFree;

    //conditions
    private boolean isNew;
    private boolean isUsed;

    private BookCondition conditionNew;
    private BookCondition conditionUsed;

    //price
    private int priceMin;
    private int priceMax;

    //sorting
    boolean priceLowHigh;
    boolean priceHighLow;
    boolean newestFirst;

    //text query
    String textQuery;

    //Category
    Category category;

    public SearchOfferDTO() {
        isSale = true;
        isExchange = true;
        isFree = true;

        typeSale = new OfferType();
        typeSale.setId(1L);
        typeSale.setType("Sprzedaż");

        typeExchange = new OfferType();
        typeExchange.setId(2L);
        typeExchange.setType("Wymiana");

        typeFree = new OfferType();
        typeFree.setId(3L);
        typeFree.setType("Za darmo");


        isNew = true;
        isUsed = true;

        conditionNew = new BookCondition();
        conditionNew.setId(1L);
        conditionNew.setValue("Nowa");

        conditionUsed = new BookCondition();
        conditionUsed.setId(2L);
        conditionUsed.setValue("Używana");

        priceMin = 0;
        priceMax = 9999;

        priceLowHigh = false;
        priceHighLow = false;
        newestFirst = true;

        //all categories
        category = null;
    }

    public List<OfferType> getOfferTypes() {
        ArrayList<OfferType> offerTypes = new ArrayList<>();
        if (isSale) offerTypes.add(typeSale);
        if (isExchange) offerTypes.add(typeExchange);
        if (isFree) offerTypes.add(typeFree);
        return offerTypes;
    }

    public List<BookCondition> getConditions() {
        ArrayList<BookCondition> conditions = new ArrayList<>();
        if (isNew) conditions.add(conditionNew);
        if (isUsed) conditions.add(conditionUsed);
        return conditions;
    }

}
