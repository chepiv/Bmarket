package com.zpi.bmarket.bmarket.DTO;

import lombok.Getter;
import lombok.Setter;

import com.zpi.bmarket.bmarket.domain.User;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginDTO {
    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;


}
