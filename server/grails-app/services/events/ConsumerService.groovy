package events

import com.security.Sprocket
import grails.events.annotation.Subscriber
import grails.events.annotation.gorm.Listener
import grails.events.bus.EventBusAware
import grails.plugin.springsecurity.SpringSecurityService
import org.grails.datastore.mapping.engine.event.AbstractPersistenceEvent
import org.grails.datastore.mapping.engine.event.PostUpdateEvent
import org.grails.datastore.mapping.engine.event.PreInsertEvent
import org.grails.datastore.mapping.engine.event.PreUpdateEvent

import javax.annotation.PostConstruct

//class ConsumerService {
class ConsumerService implements EventBusAware {

    SpringSecurityService springSecurityService

    @Subscriber('user.event')
    void logIt(Map event) {
        log.error(event.context)
        log.error(event.username)
        log.error(event.agent)
    }

    @PostConstruct
    void init() {
        eventBus.subscribe("user.event2") { Map event ->
            log.error(event.context)
            log.error(event.username)
            log.error(event.agent)
        }
    }

    Long sprocketId(AbstractPersistenceEvent event) {
        if (event.entityObject instanceof Sprocket) {
            return ((Sprocket) event.entityObject).id
        }
        null
    }


    @Subscriber
    void afterUpdate(PostUpdateEvent event) {
        Long sprocketId = sprocketId(event)
        if (sprocketId) {
            log.error "After sprocket update..."
            log.error("sprocket $sprocketId, updated")
        }
    }

    @Listener(Sprocket)
    void onSprocketPreInsert(PreInsertEvent event) {
        log.error "Before sprocket insert..."
        log.error(event.entityAccess.getProperty('material'))
        event.entityAccess.setProperty('manufacturer', "Spacely Sprockets")
    }

    @Listener(Sprocket)
    void onSprocketPreUpdate(PreUpdateEvent event) {
        log.error "Before sprocket insert..."
        log.error(event.entityAccess.getProperty('material'))
        event.entityAccess.setProperty('manufacturer', "Cogswell's Cogs")
    }
}
