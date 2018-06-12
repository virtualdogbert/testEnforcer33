import com.security.RequireCsrfProtectionMatcher
import com.security.enforcer.InstalledEnforcerService
import com.security.mfa.AuthenticationSuccessHandler
import com.virtualdogbert.UserPasswordEncoderListener
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
    authenticationSuccessHandler(AuthenticationSuccessHandler) {
        it.autowire = true
        csrfFilter = ref('csrfFilter')
    }
}
