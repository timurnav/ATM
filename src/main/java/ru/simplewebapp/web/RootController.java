package ru.simplewebapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.simplewebapp.AuthenticatedAccount;
import ru.simplewebapp.service.AccountService;
import ru.simplewebapp.util.exception.AtmException;

@Controller
public class RootController {

    @Autowired
    @Qualifier(value = "accountDetailService")
    AccountService service;

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String index() {
        return "redirect:cabinet";
    }

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String cabinet(Model model) {
        try {
            model.addAttribute("card", AuthenticatedAccount.get().getNumber());
            return "/cabinet";
        } catch (AtmException e) {
            model.addAttribute("message", e.getMessage());
            return "/failed";
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        if (AuthenticatedAccount.unSafeGet() != null) {
            return "redirect:cabinet";
        }

        return "login";
    }
}
