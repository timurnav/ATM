package ru.simplewebapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.simplewebapp.service.AccountService;
import ru.simplewebapp.util.PasswordUtil;

@Component("authenticationProvider")
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    AccountService accountService;

    public LimitLoginAuthenticationProvider() {
        super();
        setPasswordEncoder(PasswordUtil.getPasswordEncoder());
    }

    @Autowired
    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        try {
            Authentication auth = super.authenticate(authentication);
            //if reach here, means login success, else an exception will be thrown
            accountService.resetFailAttempts(authentication.getName());
            return auth;
        } catch (BadCredentialsException e) {
            //invalid login, update to user_attempts
            int attempts = accountService.incrementFailAttempt(authentication.getName());

            String message = String.format("" +
                    "%d attempt%s left before your account will be blocked",
                    attempts, attempts == 1 ? "" : "s");
            throw new BadCredentialsException(message, e.getCause());
        }

    }

}