package ru.lobakina.educationalquizapp.service.security.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@RequiredArgsConstructor
public class ApplicationUserDetails implements UserDetails {

    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final String username;
    private final Integer id;
    private final Boolean enabled;
    private final Boolean accountNotExpired;
    private final Boolean accountNotLocked;
    private final Boolean credentialsNotExpired;

    public ApplicationUserDetails(final Integer id,
                                  final String username,
                                  final String password,
                                  final Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNotExpired = true;
        this.accountNotLocked = true;
        this.credentialsNotExpired = true;
        this.enabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNotExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNotLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return accountNotExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Integer getUserId() {
        return id;
    }
}
