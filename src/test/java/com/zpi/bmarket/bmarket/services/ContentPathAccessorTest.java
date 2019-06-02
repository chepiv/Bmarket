package com.zpi.bmarket.bmarket.services;

import org.junit.Test;

/**
 * Created by chepiv on 20/05/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
public class ContentPathAccessorTest {

    @Test
    public void test() {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("os.name"));

        ContentPathAccessor.getFilesPath();
    }
}