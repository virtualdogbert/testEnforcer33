package testenforcer33

import com.security.enforcer.UserService
import grails.plugin.springsecurity.SecurityFilterPosition
import grails.plugin.springsecurity.SpringSecurityUtils

class BootStrap {

    UserService userService

    def init    = { servletContext ->
        SpringSecurityUtils.clientRegisterFilter('csrfFilter', SecurityFilterPosition.LAST.order - 100)
        userService.initUsers()
    }
    def destroy = {
    }
}
