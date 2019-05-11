package com.zpi.bmarket.bmarket.domain;

/**
 * Created by chepiv on 23/03/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
public enum Condition {
    BAD("zły"),
    MEDIUM("średni"),
    GOOD("dobry");

    //TODO: zrobić żeby można było importować nazwy w widokach, w tej chwili nie działa

    private final String displayName;

    Condition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
