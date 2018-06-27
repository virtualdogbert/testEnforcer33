package com.security

import org.springframework.security.web.util.matcher.RequestMatcher

import javax.servlet.http.HttpServletRequest
import java.util.regex.Pattern

class RequireCsrfProtectionMatcher implements RequestMatcher {

    // Always allow the HTTP GET method
    private Pattern allowedMethods = Pattern.compile('^GET$');

    @Override
    boolean matches(HttpServletRequest request) {

        if (allowedMethods.matcher(request.getMethod()).matches()) {
            return false
        }

        // Your logic goes here
        List ignoreUris = [
                'logout',
                'user',
                'role',
                'securityInfo',
                'registrationCode',
                'csrf'
        ]

        for (String uri : ignoreUris) {
            if (request.requestURI.contains(uri)) {
                return false
            }
        }

        return true
    }

}