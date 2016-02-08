package ru.simplewebapp.web;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.simplewebapp.LoggerWrapper;
import ru.simplewebapp.util.exception.AtmException;
import ru.simplewebapp.util.exception.NotFoundException;
import ru.simplewebapp.util.exception.WrongPinException;

import javax.servlet.http.HttpServletRequest;

public interface ExceptionInfoHandler {
    LoggerWrapper LOG = LoggerWrapper.get(ExceptionInfoHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE)
    default String handleError(HttpServletRequest req, NotFoundException e) {
        LOG.error("Exception at request " + req.getRequestURL());
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(WrongPinException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE)
    default String wrongPin(WrongPinException e) {
        LOG.error("User entered incorrect pin code");
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AtmException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE)
    default String wrongOperation(HttpServletRequest req, AtmException e) {
        LOG.error("Exception at request " + req.getRequestURL());
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AtmException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE)
    default String handleError(HttpServletRequest req, AtmException e) {
        LOG.error("Exception at request " + req.getRequestURL());
        return e.getMessage();
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    default String conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        LOG.error("Exception at request " + req.getRequestURL());
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @Order(Ordered.LOWEST_PRECEDENCE)
    default String handleError(HttpServletRequest req, Exception e) {
        LOG.error("Exception at request " + req.getRequestURL());
        return e.getMessage();
    }
}
