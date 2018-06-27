package testenforcer33

import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class CsrfController {

    def index() { }

    def without(){
        render 'Form was saved, no protection'
    }

    def with(){
        withForm {
            render 'Form was saved'
        }.invalidToken {
            render 'Invalid token, CSRF or double submission'
        }
    }
}
