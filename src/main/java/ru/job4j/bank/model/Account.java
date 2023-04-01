package ru.job4j.bank.model;

import lombok.Data;

/**
 * @author: Egor Bekhterev
 * @date: 01.04.2023
 * @project: job4j_bank
 */
@Data
public class Account extends Id {

    private String requisite;

    private double balance;

    private User user;

    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }
}
