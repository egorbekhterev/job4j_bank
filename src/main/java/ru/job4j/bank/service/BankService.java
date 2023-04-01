package ru.job4j.bank.service;

import ru.job4j.bank.model.Account;
import ru.job4j.bank.model.User;

import java.util.Optional;

/**
 * @author: Egor Bekhterev
 * @date: 01.04.2023
 * @project: job4j_bank
 */
public interface BankService {

    void addUser(User user);

    void addAccount(String passport, Account account);

    Optional<User> findByPassport(String passport);

    Optional<Account> findByRequisiteAndPassport(String passport, String requisite);

    boolean transferMoney(String srcPassport, String srcRequisite,
                          String destPassport, String destRequisite, double amount);
}
