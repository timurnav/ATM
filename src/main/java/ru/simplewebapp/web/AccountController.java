package ru.simplewebapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.simplewebapp.AuthenticatedAccount;
import ru.simplewebapp.model.Account;
import ru.simplewebapp.service.AccountService;
import ru.simplewebapp.util.AccountTO;

@RestController
public class AccountController {

    @Autowired
    @Qualifier(value = "accountDetailService")
    AccountService service;

    @RequestMapping(value = "/card", method = RequestMethod.POST)
    public ResponseEntity checkCardNumber(Model model,
                                          @RequestParam String card) {

        if (service.checkPresent(card)) {
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/balance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountTO get() {
        String number = AuthenticatedAccount.get().getNumber();
        Account account = service.getBalance(number);
        return new AccountTO(account);
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public void withdraw(@RequestParam int amount) {
        String number = AuthenticatedAccount.get().getNumber();
        service.withdraw(number, amount);
    }

}
