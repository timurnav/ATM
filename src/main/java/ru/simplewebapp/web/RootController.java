package ru.simplewebapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
        return "/index";
    }

    @RequestMapping(value = "/pin_enter", method = RequestMethod.POST)
    public String showEnterPinPage(Model model,
                                   @RequestParam String card) {

        if (service.checkCardNumber(card)) {
            model.addAttribute("card", card);
            return "/pin_enter";
        }
        model.addAttribute("message", "Card isn't found");
        return "/failed";
    }

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String cabinet() {
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("===============================================");
        return "/failed";
    }

    @RequestMapping(value = "/cabinet", method = RequestMethod.POST)
    public String checkPinCodeAndEnterToSystem(Model model,
                                               @RequestParam(required = false) String pin,
                                               @RequestParam(required = false) String card) {
        try {
            Account account = service.checkAndGetAccount(card, pin);
            model.addAttribute("card", account.getNumber());
            return "/cabinet";
        } catch (LockedAccountException | WrongPinException exception) {
            model.addAttribute("message", exception.getMessage());
            return "/failed";
        }

    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {

        model.put("error", error);
        model.put("message", message);
        return "login";
    }
}
