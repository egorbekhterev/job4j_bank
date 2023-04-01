package ru.job4j.bank.model;

import lombok.Data;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: Egor Bekhterev
 * @date: 01.04.2023
 * @project: job4j_bank
 */
@Data
public class User extends Id {

    private String passport;

    private String username;

    private List<Account> accounts = new CopyOnWriteArrayList<>();

    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }
}
