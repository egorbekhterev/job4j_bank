package ru.job4j.bank.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.bank.model.Account;

import java.util.Optional;

/**
 * @author: Egor Bekhterev
 * @date: 01.04.2023
 * @project: job4j_bank
 */
@Repository
public class AccountRepository extends Store<Account> {

    public Optional<Account> findByRequisiteAndPassport(String requisite, String passport) {
        return store.values().stream()
                .filter(account -> account.getRequisite().equals(requisite)
                        && account.getUser().getPassport().equals(passport))
                .findFirst();
    }
}
