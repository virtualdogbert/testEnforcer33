package com.security.mfa

import groovy.transform.CompileStatic
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

@CompileStatic
class MfaAuthenticationProvider implements AuthenticationProvider {
    UserDetailsService userDetailsService

    /**
     * Much of this is copied directly from
     * {@link org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider}
     */
    Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MfaAuthenticationToken authToken = (MfaAuthenticationToken) authentication
        String username = (authToken.principal == null) ? 'NONE_PROVIDED' : authToken.name
        UserDetails user = userDetailsService.loadUserByUsername(username)

        Boolean verifiedResponse = authToken.tokenResponse == '1234'

        if (!verifiedResponse) {
            throw new WrongTokenResponse("Incorrect text message response from ${username}")
        }

        MfaAuthenticationToken result = new MfaAuthenticationToken(user, authentication.credentials, user.authorities)
        result.details = authentication.details

        return result
    }

    boolean supports(Class<? extends Object> authentication) {
        return (MfaAuthenticationToken.isAssignableFrom(authentication))
    }
}
