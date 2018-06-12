package testenforcer33

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.boot.web.servlet.ServletContextInitializer

import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.SessionCookieConfig

class Application extends GrailsAutoConfiguration implements ServletContextInitializer {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Override
    void onStartup(ServletContext servletContext) throws ServletException {
        SessionCookieConfig config = servletContext.getSessionCookieConfig()

        config.setName('sid')
        config.httpOnly = true
        config.secure = true
    }
}