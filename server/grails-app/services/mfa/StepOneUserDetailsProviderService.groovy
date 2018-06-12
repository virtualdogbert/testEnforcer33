package mfa

import com.security.User
import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import groovy.transform.CompileStatic
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@CompileStatic
class StepOneUserDetailsProviderService extends GormUserDetailsService {

    public static final String ROLE_STEP_ONE_AUTHENTICATED = "ROLE_PRE_AUTH"
    public static final List<GrantedAuthority> PRE_AUTH_ROLES = [(GrantedAuthority) new SimpleGrantedAuthority(ROLE_STEP_ONE_AUTHENTICATED)]

    @Override
    protected UserDetails createUserDetails(user, Collection<GrantedAuthority> authorities) {
        if(((User)user).mfaEnabled) {
            return super.createUserDetails(user, PRE_AUTH_ROLES)
        }

        return super.createUserDetails(user, authorities)
    }
}