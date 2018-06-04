import com.security.enforcer.InstalledEnforcerService
import com.virtualdogbert.UserPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)

    enforcerService(InstalledEnforcerService) {
        grailsApplication = ref('grailsApplication')
        springSecurityService = ref('springSecurityService')
    }
}
