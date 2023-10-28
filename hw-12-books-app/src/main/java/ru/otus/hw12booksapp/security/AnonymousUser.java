package ru.otus.hw12booksapp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.otus.hw12booksapp.entity.Role;

import java.util.Collection;
import java.util.Set;

public class AnonymousUser implements UserDetails {

    private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new Role(ROLE_ANONYMOUS));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return "anonymous";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}