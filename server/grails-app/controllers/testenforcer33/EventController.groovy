package testenforcer33

import events.PublisherService
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
class EventController {
    static           responseFormats = ['json', 'xml']
    PublisherService publisherService

    def index() {
        render publisherService.event("Something happened!")
    }

    def event2(){
        render publisherService.event2("Something happened!")
    }

}
