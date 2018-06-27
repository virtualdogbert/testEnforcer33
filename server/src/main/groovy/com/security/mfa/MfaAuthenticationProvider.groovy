package com.security.mfa

import com.security.User
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.userdetails.GrailsUser
import groovy.transform.CompileStatic
import org.jboss.aerogear.security.otp.Totp
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
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
        GrailsUser user = (GrailsUser)userDetailsService.loadUserByUsername(username)

        Boolean verifiedResponse = verify((Long)user.id, authToken.tokenResponse)

        if (!verifiedResponse) {
            throw new WrongTokenResponse("Incorrect text message response from ${username}")
        }

        MfaAuthenticationToken result = new MfaAuthenticationToken(user, authentication.credentials, user.authorities)
        result.details = authentication.details

        return result
    }

    @Transactional
    boolean verify(Long id, String token) {
        User user = User.get(id)
        String multiFactorSecret = user.mfa

        if (!multiFactorSecret) {
            throw new WrongTokenResponse("MFA not setup for ${user.username}")
        }

        Totp googleAuthenticator = new Totp(multiFactorSecret)
        return googleAuthenticator.verify(token)
    }

    boolean supports(Class<? extends Object> authentication) {
        return (MfaAuthenticationToken.isAssignableFrom(authentication))
    }
}
