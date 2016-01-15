package ru.simplewebapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RootController {

    //make stateless
    private static int attempt = 0;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/cards", method = RequestMethod.POST)
    public String cards(Model model,
                        @RequestParam(name = "card") long cardNumber) {
        //mock
        long mockCardNumber = 1111111111111111L;

        if (cardNumber == mockCardNumber) {
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
        //mock
        int mockPinCcode = 1234;

        if (pin == mockPinCcode) {
            attempt = 0;
            return "operations";
        }
        if (attempt++ < 3) {
            model.addAttribute("card", cardNumber);
            model.addAttribute("message", "incorrect pin code");
            return "card";
        }
        model.addAttribute("message", "Card is blocked");
        return "failed";
    }

}
