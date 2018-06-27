package testenforcer33.security

import mfa.StepOneUserDetailsProviderService
import com.security.mfa.MfaAuthenticationFilter
import grails.plugin.springsecurity.SpringSecurityUtils

class LoginController extends grails.plugin.springsecurity.LoginController {

    def denied() {
        if (SpringSecurityUtils.ifAnyGranted(StepOneUserDetailsProviderService.ROLE_STEP_ONE_AUTHENTICATED)) {
            redirect action: 'steptwo'
        }
    }


    def steptwo() {
        [
                postUrl  : "${request.contextPath}/${SpringSecurityUtils.securityConfig.mfa.filterProcessesUrl}",
                tokenName: MfaAuthenticationFilter.TOKEN_RESPONSE_KEY
        ]
    }
}