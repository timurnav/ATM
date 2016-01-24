package ru.simplewebapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.simplewebapp.AuthenticatedAccount;
import ru.simplewebapp.service.AccountService;
import ru.simplewebapp.util.exception.LockedAccountException;
import ru.simplewebapp.util.exception.WrongPinException;

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
        } catch (LockedAccountException | WrongPinException exception) {
            model.addAttribute("message", exception.getMessage());
            return "/failed";
        }

    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {

        if (AuthenticatedAccount.unSafeGet() != null) {
            return "redirect:cabinet";
        }

        if (error || message != null) {
            model.put("error", error);
            model.put("message", message);
            return "failed";
        }
        return "login";
    }
}
