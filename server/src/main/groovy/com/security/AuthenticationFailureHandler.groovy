package com.security

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.web.authentication.AjaxAwareAuthenticationFailureHandler
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.core.AuthenticationException

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFailureHandler extends AjaxAwareAuthenticationFailureHandler {
    MessageSource messageSource

    @Override
    void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if (SpringSecurityUtils.isAjax(request)) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED

            String msg = ''
            if (exception) {
                if (exception instanceof AccountExpiredException) {
                    msg = messageSource.getMessage("springSecurity.errors.login.expired", null, LocaleContextHolder.locale)
                } else {
                    msg = messageSource.getMessage("springSecurity.errors.login.fail", null, LocaleContextHolder.locale)
                }
            }

            response << ([status: "error", message: msg] as JSON)
        } else {
            super.onAuthenticationFailure(request, response, exception)
        }
    }
}
