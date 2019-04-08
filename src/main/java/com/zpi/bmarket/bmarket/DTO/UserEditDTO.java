package com.zpi.bmarket.bmarket.DTO;

import com.zpi.bmarket.bmarket.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserEditDTO {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String surname;

    //@NotNull
    //@NotEmpty
    //private Date birthDate;

    //implements password encryption
    public User getUser() {
        User u = new User();
        return u;
    }
}
