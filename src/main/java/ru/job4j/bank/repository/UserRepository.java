package ru.job4j.bank.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.bank.model.User;

import java.util.Optional;

/**
 * @author: Egor Bekhterev
 * @date: 01.04.2023
 * @project: job4j_bank
 */
@Repository
public class UserRepository extends Store<User> {

    public Optional<User> findByPassport(String passport) {
        return store.values().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }
}
