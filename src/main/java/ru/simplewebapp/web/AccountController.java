package ru.simplewebapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.simplewebapp.AuthenticatedAccount;
import ru.simplewebapp.model.Account;
import ru.simplewebapp.service.AccountService;
import ru.simplewebapp.util.AccountTO;
import ru.simplewebapp.util.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class AccountController {

    @Autowired
    @Qualifier(value = "accountDetailService")
    AccountService service;

    @RequestMapping(value = "/card", method = RequestMethod.POST)
    public ResponseEntity checkCardNumber(@RequestParam String card) {
        try {
            service.checkPresent(card);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/card", method = RequestMethod.GET)
    public ResponseEntity checkPincode(HttpServletRequest request,
            @RequestParam(value = "error", required = false) boolean error) {

        if (error) {
            HttpSession session = request.getSession();
            Exception exception = (Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            String message = exception.getMessage();
            return new ResponseEntity(message, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
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
