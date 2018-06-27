package mfa

import com.security.User
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import groovy.transform.CompileStatic
import org.apache.commons.lang.RandomStringUtils

@CompileStatic
@Transactional
class MfaService{

    SpringSecurityService springSecurityService

    String setupMfa(){
        User user = (User)springSecurityService.currentUser
        user.mfaEnabled = true
        user.mfa = RandomStringUtils.randomAlphabetic(32)
        user.save()

        return "otpauth://totp/localhost%3A${URLEncoder.encode(user.username, "UTF-8")}?secret=$user.mfa&issuer=VirtualDogbert"
    }
}