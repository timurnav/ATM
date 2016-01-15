package ru.simplewebapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.simplewebapp.model.Account;
import ru.simplewebapp.service.CardService;

@Controller
public class RootController {

    @Autowired
    CardService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/cards", method = RequestMethod.POST)
    public String cards(Model model,
                        @RequestParam(name = "card") long cardNumber) {

        if (service.checkCardNumber(cardNumber)) {
            model.addAttribute("card", cardNumber);
            return "card";
        }
        model.addAttribute("message", "Card isn't found");
        return "failed";
    }

    @RequestMapping(value = "/pin", method = RequestMethod.POST)
    public String pinCode(Model model,
                          @RequestParam(name = "pin") int pin,
                          @RequestParam(name = "card") long cardNumber) {

        Account account = service.getAccount(cardNumber);

        if (account.getAttempt() < 3) {
            if (pin == account.getPinCode()) {
                account.dropAttempts();
                return "operations";
            }
            account.incrementAttempt();
            model.addAttribute("card", cardNumber);
            model.addAttribute("message", "incorrect pin code.");
            return "card";
        }
        model.addAttribute("message", "Card is blocked");
        return "failed";
    }

}