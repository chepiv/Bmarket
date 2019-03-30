package com.zpi.bmarket.bmarket.services;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

// TODO: 30.03.2019 do it more advanced and safe?
@Service
public class Encryption {

    public static String encrypt(String data) {

        byte[] s = Base64.encodeBase64(data.getBytes());
        return new String(s);

    }

    public static String decrypt(String value) {
        byte[] s = Base64.decodeBase64(value);
        return new String(s);
    }
}
