package ru.itis.security.oauth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.itis.security.details.UserDetailsImpl;

@Component
public class OAuthAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    public OAuthAuthenticationProvider(@Qualifier("oAuthUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OAuthAuthentication authAuthentication = (OAuthAuthentication) authentication;
        UserDetailsImpl userDetails = (UserDetailsImpl)userDetailsService.loadUserByUsername(authAuthentication.getName());
        authAuthentication.setUserDetails(userDetails);
        authAuthentication.setAuthenticated(true);
        authAuthentication.setName(userDetails.getUsername());
        return authAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OAuthAuthentication.class.equals(authentication);
    }
}
