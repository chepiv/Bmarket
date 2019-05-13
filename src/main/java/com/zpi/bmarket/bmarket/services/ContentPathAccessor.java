package com.zpi.bmarket.bmarket.services;

import org.springframework.stereotype.Service;

/**
 * Created by chepiv on 12/05/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
@Service
public class ContentPathAccessor {
    public static String getContentPath() {
        return "var/opt/oss/content/";
    }
}
