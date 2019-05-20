package com.zpi.bmarket.bmarket.DTO;

import com.zpi.bmarket.bmarket.domain.Address;
import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.AddressRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
public class UserDTO {

    @Autowired
    AddressRepository addressRepository;

    private String name;

    private String surname;

    private String password;

    private String matchingPassword;

    private String phoneNumber;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private String birthDateString;

    private String avatarUrl;

    private Address address;

    private String email;

    private String city;
    private String streetAddress;
    private String zipCode;

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

    public void setPassword(User user){
        user.setPassword(this.password);
    }

    public void setNameSurnamePhoneAvatar(User user) throws ParseException {
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setPhoneNumber(this.phoneNumber);
        user.setAvatarUrl(this.avatarUrl);

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter1.parse(birthDateString);
        user.setBirthDate(date);
    }

    public Address createAddress(){
        Address address = new Address();
        address.setCity(this.city);
        address.setStreetAddress(this.streetAddress);
        address.setZipCode(this.zipCode);
        //addressRepository.save(address);
        return address;
    }

    //implements password encryption
    public User getUser() {
        User u = new User();
        return u;
    }

    public void setAddressString(Address address){
        this.city = address.getCity();
        this.streetAddress = address.getStreetAddress();
        this.zipCode = address.getZipCode();
    }
}
