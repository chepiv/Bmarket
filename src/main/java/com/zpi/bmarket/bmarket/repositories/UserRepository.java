package com.zpi.bmarket.bmarket.repositories;

import com.zpi.bmarket.bmarket.domain.Offer;
import com.zpi.bmarket.bmarket.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by chepiv on 18/03/2019.
 * Contact: chepurin.ivan@gmail.com
 * Github:chepiv
 */
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByLoginAndPassword(String login, String password);
    User findByOffers(Offer offer);
}
