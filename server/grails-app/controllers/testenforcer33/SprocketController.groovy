package testenforcer33

import com.security.Sprocket
import com.security.enforcer.EnforcerTestTSService
import com.security.enforcer.SprocketService
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class SprocketController {

    EnforcerTestTSService enforcerTestTSService
    SprocketService sprocketService


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

    def update(){
        Sprocket sprocket = enforcerTestTSService.createSprocket()
        errorsHandler([enforcerTestTSService.updateSprocketFailure(sprocket)])
    }

    def data(){
        Sprocket sprocket = sprocketService.getSprocket(1)
        println sprocket.manufacturer
        println sprocket.material

    }


}
