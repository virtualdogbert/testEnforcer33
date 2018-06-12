package com.security.mfa

import groovy.transform.CompileStatic
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

@CompileStatic
class MfaAuthenticationToken extends AbstractAuthenticationToken {
    String tokenResponse
    Object credentials
    Object principal

    MfaAuthenticationToken(Object principal, Object credentials, String tokenResponse) {
        super(null)
        this.principal = principal
        this.credentials = credentials
        this.tokenResponse = tokenResponse
        this.setAuthenticated(false)
    }

    MfaAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities)
        this.principal = principal
        this.credentials = credentials
        super.setAuthenticated(true)
    }

    @Override
    void setAuthenticated(boolean isAuthenticated) {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    'Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead')
        }
        super.setAuthenticated(false)
    }

    @Override
    Object getCredentials() {
        return credentials
    }

    @Override
    Object getPrincipal() {
        return principal
    }
}
