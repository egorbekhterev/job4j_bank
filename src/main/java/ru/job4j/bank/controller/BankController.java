package ru.job4j.bank.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.bank.service.BankService;

import java.util.Map;

/**
 * @author: Egor Bekhterev
 * @date: 01.04.2023
 * @project: job4j_bank
 */
@RestController
@RequestMapping("/bank")
@AllArgsConstructor
public class BankController {

    private final BankService bankService;

    @PostMapping
    public void transfer(@RequestBody Map<String, String> body) {
        var srcPassport = body.get("srcPassport");
        var srcRequisite = body.get("srcRequisite");
        var destPassport = body.get("destPassport");
        var destRequisite = body.get("destRequisite");
        var amount = Double.parseDouble(body.get("amount"));
        bankService.transferMoney(srcPassport, srcRequisite, destPassport, destRequisite, amount);
    }
}
