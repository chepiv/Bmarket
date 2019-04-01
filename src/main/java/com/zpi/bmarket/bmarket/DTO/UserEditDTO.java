package com.zpi.bmarket.bmarket.DTO;

import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.services.Encryption;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserEditDTO {
    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    private String email;

    //implements password encryption
    public User getUser() {
        User u = new User();
        u.setLogin(login);
        u.setPassword(Encryption.encrypt(password));
        u.setEmail(email);
        return u;
    }
}
