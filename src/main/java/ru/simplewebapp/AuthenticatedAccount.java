package ru.simplewebapp;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.simplewebapp.model.Account;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import static java.util.Objects.requireNonNull;

public class AuthenticatedAccount implements UserDetails, Serializable {

    private String number;
    private boolean enabled;
    private String pin;


    public AuthenticatedAccount(Account account) {
        this.number = account.getNumber();
        this.pin = account.getPin();
        this.enabled = account.getAttempt() < Account.MAX_ATTEMPTS;
    }

    public AuthenticatedAccount() {
    }

    public static AuthenticatedAccount unSafeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object acc = auth.getPrincipal();
        return (acc instanceof AuthenticatedAccount) ?
                (AuthenticatedAccount) acc : null;
    }

    public static AuthenticatedAccount get() {
        AuthenticatedAccount acc = unSafeGet();
        requireNonNull(acc, "No authorized account found");
        return acc;
    }

    public String getNumber() {
        return get().number;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> "USER");
    }

    @Override
    public String getPassword() {
        return pin;
    }

    @Override
    public String getUsername() {
        return number;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
