package events

import grails.events.EventPublisher
import grails.events.annotation.Publisher
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.web.SecurityRequestHolder

import javax.servlet.http.HttpServletRequest

//class PublisherService {
class PublisherService implements EventPublisher {
    SpringSecurityService springSecurityService

    @Publisher('user.event')
    Map event(String context) {
        HttpServletRequest request = SecurityRequestHolder?.request

        [
                context : context,
                username: springSecurityService.currentUser.username,
                agent   : request?.getHeader('user-agent')
        ]
    }

    Map event2(String context) {
        HttpServletRequest request = SecurityRequestHolder?.request
        Map data = [
                context : context,
                username: springSecurityService.currentUser.username,
                agent   : request?.getHeader('user-agent')
        ]

        notify('user.event2', data)
        return data
    }
}
