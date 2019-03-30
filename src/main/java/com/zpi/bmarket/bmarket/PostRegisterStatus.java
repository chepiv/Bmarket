package com.zpi.bmarket.bmarket;

public enum PostRegisterStatus {
    SUCCESS(),
    LOGIN_DUP,
    EMAIL_DUP,
    PASSWORDS_NOT_MATCH,
    PASSWORD_WEAK,
    EMAIL_NOT_VALID;
    // TODO: 30.03.2019 other cases?
}
