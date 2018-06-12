package com.security.mfa

import grails.plugin.springsecurity.userdetails.GrailsUser
import groovy.transform.CompileStatic
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@CompileStatic
class MfaAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    final static String TOKEN_RESPONSE_KEY = 'token'

    MfaAuthenticationFilter() {
        super('/mfa')
    }

    @Override
    Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        logger.error("Attempting text message authentication")

        if (!request.post) {
            throw new AuthenticationServiceException("Authentication method not supported: $request.method")
        }

        String userName = ((GrailsUser)SecurityContextHolder.context?.authentication?.principal).username
        String tokenResponse = request.getParameter(TOKEN_RESPONSE_KEY)

        MfaAuthenticationToken authentication = new MfaAuthenticationToken(userName, null, tokenResponse)
        Authentication authToken = authenticationManager.authenticate(authentication)

        return authToken
    }
}
