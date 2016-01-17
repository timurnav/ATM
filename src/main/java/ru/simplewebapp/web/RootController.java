package ru.simplewebapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.simplewebapp.model.Account;
import ru.simplewebapp.service.AccountService;

@Controller
public class RootController {

    @Autowired
    AccountService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/cards", method = RequestMethod.POST)
    public String cards(Model model,
                        @RequestParam(name = "card") String cardNumber) {

        if (service.checkCardNumber(cardNumber)) {
            model.addAttribute("card", cardNumber);
            return "card";
        }
        model.addAttribute("message", "Card isn't found");
        return "failed";
    }

    @RequestMapping(value = "/balance", method = RequestMethod.POST)
    public String balance(Model model,
                        @RequestParam(name = "card") String number) {
        Account account = service.getAccountByNumber(number);
        model.addAttribute("account", account);
        return "balance";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdraw(Model model,
                          @RequestParam(name = "card") long cardNumber) {

        model.addAttribute("card", cardNumber);
        return "withdraw";
    }

    @RequestMapping(value = "/withdraw_result", method = RequestMethod.POST)
    public String withdrawResult(Model model,
                           @RequestParam(name = "card") String cardNumber,
                           @RequestParam(name = "sum") int sum) {

        Account accoutAfterWithdraw = service.withdraw(cardNumber, sum);
        model.addAttribute("account", accoutAfterWithdraw);
        model.addAttribute("card", cardNumber);
        model.addAttribute("sum", sum);

        return "withdraw_result";
    }

    @RequestMapping(value = "/pin", method = RequestMethod.POST)
    public String pinCode(Model model,
                          @RequestParam(name = "pin") String pin,
                          @RequestParam(name = "card") String cardNumber) {

        Account account = service.checkAndGetAccount(cardNumber, pin);

        if (account.getAttempt() == 0) {
            account.dropAttempts();
            return "operations";
        }
        if (account.getAttempt() < 4) {
            model.addAttribute("card", cardNumber);
            model.addAttribute("message", "incorrect pin code.");
            return "card";
        }
        model.addAttribute("message", "Card is blocked");
        return "failed";
    }

    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public String exit(Model model) {
        return "redirect:/";
    }

}
