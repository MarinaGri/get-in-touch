package ru.itis.security.oauth;


import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import ru.itis.security.details.UserDetailsImpl;

import java.util.Collection;

@Data
public class OAuthAuthentication implements Authentication {

    private String code;

    private boolean authenticated;

    private UserDetailsImpl userDetails;

    public OAuthAuthentication(String code){
        this.code = code;
        this.userDetails = new UserDetailsImpl(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return userDetails.getPassword();
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return code;
    }

    public void setName(String code){
        this.code = code;
    }

    public void setUserDetails(UserDetailsImpl userDetails) {
        this.userDetails = userDetails;
    }
}

