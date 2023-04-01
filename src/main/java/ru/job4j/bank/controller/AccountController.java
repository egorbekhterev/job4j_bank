package ru.job4j.bank.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.bank.model.Account;
import ru.job4j.bank.service.BankService;

import java.util.Map;

/**
 * @author: Egor Bekhterev
 * @date: 01.04.2023
 * @project: job4j_bank
 */
@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final BankService bankService;

    @PostMapping
    public Account addAccount(@RequestBody Map<String, String> body) {
        var passport = body.get("passport");
        var account = new Account(body.get("requisite"), 0);
        bankService.addAccount(passport, account);
        return account;
    }

    /**
     * Этот метод является GET-эндпоинтом, который получает информацию об аккаунте по его номеру паспорта и реквизитам.
     * ResponseStatusException в конструкторе принимает HTTP статус и сообщение, которое нужно вывести пользователю.
     * @param passport Номер паспорта, связанный с аккаунтом.
     * @param requisite Реквизиты, связанные с аккаунтом.
     * @return Объект Account, связанный с заданными номером паспорта и реквизитами.
     * @throws ResponseStatusException если аккаунт не найден.
     */
    @GetMapping
    public Account findByRequisiteAndPassport(@RequestParam String passport, @RequestParam String requisite) {
        return bankService.findByRequisiteAndPassport(passport, requisite)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account is not found. Please, check requisites."
                ));
    }
}
