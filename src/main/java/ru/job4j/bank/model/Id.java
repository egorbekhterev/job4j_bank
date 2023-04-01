package ru.job4j.bank.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Egor Bekhterev
 * @date: 01.04.2023
 * @project: job4j_bank
 */
@Data
@NoArgsConstructor
public abstract class Id {

    protected int id;
}
