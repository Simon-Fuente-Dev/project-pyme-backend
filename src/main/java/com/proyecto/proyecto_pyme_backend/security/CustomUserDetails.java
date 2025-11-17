package com.proyecto.proyecto_pyme_backend.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final Integer idUsuario;
    private final Integer idPyme;
    private final String username;

    public CustomUserDetails(Integer idUsuario, Integer idPyme, String username) {
        this.idUsuario = idUsuario;
        this.idPyme = idPyme;
        this.username = username;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Integer getIdPyme() {
        return idPyme;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // sin roles por ahora
    }

    @Override public String getPassword() { return null; }
    @Override public String getUsername() { return username; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
