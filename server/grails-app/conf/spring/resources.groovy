import com.security.RequireCsrfProtectionMatcher
import com.security.enforcer.InstalledEnforcerService
import com.security.mfa.AuthenticationSuccessHandler
import com.security.mfa.MfaAuthenticationFilter
import com.security.mfa.MfaAuthenticationProvider
import com.virtualdogbert.UserPasswordEncoderListener
import grails.plugin.springsecurity.SpringSecurityUtils
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.web.csrf.CsrfFilter
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)

    enforcerService(InstalledEnforcerService) {
        grailsApplication = ref('grailsApplication')
        springSecurityService = ref('springSecurityService')
    }

    requireCsrfProtectionMatcher(RequireCsrfProtectionMatcher)

    csrfFilter(CsrfFilter, new HttpSessionCsrfTokenRepository()) {
        requireCsrfProtectionMatcher = ref('requireCsrfProtectionMatcher')
    }

    daoAuthenticationProvider(DaoAuthenticationProvider) {
        it.autowire = true
        userDetailsService = ref('stepOneUserDetailsProviderService')
    }

    authenticationSuccessHandler(AuthenticationSuccessHandler) {
        it.autowire = true
        csrfFilter = ref('csrfFilter')
    }


    mfaAuthenticationProvider(MfaAuthenticationProvider) {
        it.autowire = true
        userDetailsService = ref('userDetailsService')
    }

    mfaAuthenticationFilter(MfaAuthenticationFilter) {
        it.autowire = true
        filterProcessesUrl = "/$SpringSecurityUtils.securityConfig.mfa.filterProcessesUrl"
    }
}
