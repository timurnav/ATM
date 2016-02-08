package ru.simplewebapp.web;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.simplewebapp.LoggerWrapper;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final LoggerWrapper LOG = LoggerWrapper.get(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @Order(Ordered.LOWEST_PRECEDENCE)
    ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        LOG.error("Exception at request " + req.getRequestURL());
        ModelAndView mav = new ModelAndView("exception/exception");
        mav.addObject("exception", e);
        return mav;
    }
}
