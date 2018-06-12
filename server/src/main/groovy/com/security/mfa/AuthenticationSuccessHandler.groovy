package com.security.mfa

import groovy.util.logging.Slf4j
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import org.springframework.security.web.csrf.CsrfFilter
import org.springframework.security.web.csrf.CsrfToken

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    CsrfFilter csrfFilter

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws ServletException, IOException {

        log.info "Successful login event triggered: ${authentication.principal.username}"

        def session = request.getSession()
        session.trackAfterLogin = true

        CsrfToken token = csrfFilter.tokenRepository.generateToken(request)
        csrfFilter.tokenRepository.saveToken(token, request, response)
        response.setHeader(token.headerName, token.token)

        super.onAuthenticationSuccess(request, response, authentication)
    }
}