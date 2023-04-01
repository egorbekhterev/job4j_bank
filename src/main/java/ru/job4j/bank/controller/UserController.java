package ru.job4j.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.job4j.bank.model.User;
import ru.job4j.bank.service.BankService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Egor Bekhterev
 * @date: 01.04.2023
 * @project: job4j_bank
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getSimpleName());

    private final BankService bankService;

    private final ObjectMapper objectMapper;

    @PostMapping
    public User save(@RequestBody Map<String, String> body) {
        var username = body.get("username");
        var passport = body.get("passport");
        if (username == null || passport == null) {
            throw new NullPointerException("Username and password mustn't be empty.");
        }
        if (passport.length() < 10) {
            throw new IllegalArgumentException("Invalid passport details. "
                    + "Passport number length must be more than 10 characters.");
        }
        var user = new User(username, passport);
        bankService.addUser(user);
        return user;
    }

    @GetMapping
    public User findByPassport(@RequestParam String passport) {
        return bankService.findByPassport(passport).orElse(null);
    }

    /**
     * Обработчик исключений для {@link IllegalArgumentException}. Устанавливает 400 статус ответа, тип содержимого
     * JSON и записывает тело ответа в JSON-объект. Ответ состоит из сообщения и типа возникшего исключения.
     * @param e Исключение, которое было сгенерировано и перехвачено этим обработчиком.
     * @param request HTTP-сервлет запроса, который вызвал исключение.
     * @param response HTTP-сервлет ответа, который будет изменен для содержания сообщения об ошибке и типа.
     * @throws IOException если происходит исключение ввода или вывода.
     * P.S. Этот подход ограничен использованием только в классе, где он определен, поэтому его следует применять
     * только к узко специфическим исключениям, если вы уверены, что их не возникнет в других классах или их нужно
     * будет обрабатывать иначе.
     */
    @ExceptionHandler(value = { IllegalArgumentException.class })
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        LOGGER.error(e.getLocalizedMessage());
    }
}
