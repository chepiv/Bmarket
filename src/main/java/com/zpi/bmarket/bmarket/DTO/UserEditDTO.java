package com.zpi.bmarket.bmarket.DTO;

import com.zpi.bmarket.bmarket.domain.Address;
import com.zpi.bmarket.bmarket.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class UserEditDTO {

    private String name;

    private String surname;

    private String password;

    private String matchingPassword;

    private String phoneNumber;

    private Date birthDate;

    private String avatarUrl;

    private Address address;

    private String email;


    public void getCurrentDataUser(User user){
        this.name = user.getName();
        this.surname = user.getSurname();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.birthDate = user.getBirthDate();
        this.avatarUrl = user.getAvatarUrl();
        this.address = user.getAddress();
        this.email = user.getEmail();
    }

    public void setCurrentDataUser(User user){
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setPassword(this.password);
        user.setPhoneNumber(this.phoneNumber);
        user.setBirthDate(this.birthDate);
        user.setAvatarUrl(this.avatarUrl);
        user.setAddress(this.address);
    }

    //implements password encryption
    public User getUser() {
        User u = new User();
        return u;
    }
}
