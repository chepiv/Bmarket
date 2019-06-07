package com.zpi.bmarket.bmarket.repositories;


import com.zpi.bmarket.bmarket.domain.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {
    Address findByCityAndStreetAddressAndZipCode(String city, String streetAddress, String zipCode);

    boolean existsByCityAndAndStreetAddressAndZipCode(String city, String streetAddress, String zipCode);
}