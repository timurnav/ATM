package ru.simplewebapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.simplewebapp.model.Account;
import ru.simplewebapp.service.AccountService;
import ru.simplewebapp.util.exception.LockedAccountException;
import ru.simplewebapp.util.exception.WrongPinException;

@Controller
public class RootController {

    @Autowired
    AccountService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/pin_enter", method = RequestMethod.POST)
    public String showEnterPinPage(Model model,
                                   @RequestParam(name = "card") String cardNumber) {

        if (service.checkCardNumber(cardNumber)) {
            model.addAttribute("card", cardNumber);
            return "pin_enter";
        }
        model.addAttribute("message", "Card isn't found");
        return "failed";
    }

    @RequestMapping(value = "/private_cabinet", method = RequestMethod.POST)
    public String checkPinCodeAndEnterToSystem(Model model,
                                               @RequestParam(name = "pin") String pin,
                                               @RequestParam(name = "card") String cardNumber) {
        try {
            Account account = service.checkAndGetAccount(cardNumber, pin);
            model.addAttribute("card", account.getNumber());
            return "private_cabinet";
        } catch (LockedAccountException | WrongPinException exception) {
            model.addAttribute("message", exception.getMessage());
            return "failed";
        }

    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdraw(Model model,
                           @RequestParam(name = "card") long cardNumber) {

        model.addAttribute("card", cardNumber);
        return "withdraw";
    }

    @RequestMapping(value = "/withdraw_result", method = RequestMethod.POST)
    public String showWithdrawResultPage(Model model,
                                         @RequestParam(name = "card") String cardNumber,
                                         @RequestParam(name = "sum") int sum) {

        Account accountAfterWithdraw = service.withdraw(cardNumber, sum);
        model.addAttribute("account", accountAfterWithdraw);
        model.addAttribute("card", cardNumber);
        model.addAttribute("sum", sum);

        return "withdraw_result";
    }

    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public String logout(Model model) {
        return "redirect:/";
    }

}
