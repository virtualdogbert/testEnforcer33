package testenforcer33

import com.security.Sprocket
import com.security.enforcer.EnforcerTestTSService
import grails.core.GrailsApplication
import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.GrailsPluginManager
import grails.plugins.PluginManagerAware

class TestController implements PluginManagerAware {

    GrailsApplication     grailsApplication
    GrailsPluginManager   pluginManager
    EnforcerTestTSService enforcerTestTSService

    @Secured(['ROLE_USER'])
    def index() {
        println "test"
        Sprocket sprocket = enforcerTestTSService.createSprocket()
        enforcerTestTSService.updateSprocket(sprocket)


        Sprocket sprocket1 = enforcerTestTSService.createSprocket()
        enforcerTestTSService.updateSprocketCompileStatic(sprocket1)

        Sprocket sprocket2
        Sprocket sprocket3

        try {
            sprocket2 = enforcerTestTSService.createSprocket()
            enforcerTestTSService.updateSprocketException(sprocket2)
        } catch (Exception e) {
            //e.printStackTrace()
        }

        try {
            sprocket3 = enforcerTestTSService.createSprocket()
            enforcerTestTSService.updateSprocketCompileStaticException(sprocket3)
        } catch (Exception e) {
           // e.printStackTrace()
        }

        try {
            enforcerTestTSService.createSprocketException()
        } catch (Exception e) {
            //e.printStackTrace()
        }

        Sprocket.list().each{
            println "$it.id: $it.material"
        }

        [results : Sprocket.list()]
    }


}
