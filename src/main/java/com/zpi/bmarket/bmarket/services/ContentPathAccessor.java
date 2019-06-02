package com.zpi.bmarket.bmarket.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by chepiv on 12/05/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
@Service
public class ContentPathAccessor {
    private static final String BASE_DIR = System.getProperty("user.dir");
    private static final String BASE_DIR_LOCAL = BASE_DIR + "\\var\\opt\\oss\\content\\";
    private static final String FILE_PREFIX_LOCAL = "file:///";
    private static final String FILE_PREFIX_PROD = "file:";


    public static String getContentPath() {
        return "var/opt/oss/content/";
    }

    public static String getFilesPath() {
        String os = System.getProperty("os.name");
        if (StringUtils.containsIgnoreCase(os, "windows") || StringUtils.containsIgnoreCase(os, "mac os")) {
            return FILE_PREFIX_LOCAL + BASE_DIR_LOCAL;
        } else {
            return FILE_PREFIX_LOCAL + "var/opt/oss/content/";
        }
    }
}
