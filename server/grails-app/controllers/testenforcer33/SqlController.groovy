package testenforcer33

import com.security.Sprocket
import grails.core.GrailsApplication
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.GrailsPluginManager
import grails.plugins.PluginManagerAware
import groovy.sql.Sql

import javax.sql.DataSource

@Secured('permitAll')
class SqlController implements PluginManagerAware {
    DataSource dataSource

    GrailsApplication     grailsApplication
    GrailsPluginManager   pluginManager
    SpringSecurityService springSecurityService

    def index() {
        String id = "1 OR 1=1"

        try {
            println Sprocket.where { id == id }.list()
        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            println Sprocket.findAllById(id)
        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            println Sprocket.findAll("from Sprocket where id = $id")
        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            println Sprocket.findAll("from Sprocket where id = $id".toString())
        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            println Sprocket.executeQuery("from Sprocket where id = $id")
        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            println Sprocket.executeQuery("from Sprocket where id = $id".toString())
        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            println Sprocket.executeQuery("from Sprocket where id = :id", [id: id])
        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            println Sprocket.executeQuery("from Sprocket where id = :id", [id: 1l])
        } catch (Exception e) {
            e.printStackTrace()
        }

        Sql sql = new Sql(dataSource)


        try {
            println sql.rows("select * from sprocket where id=$id")
        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            println sql.rows("select * from sprocket where id=$id")
        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            println sql.rows("select * from sprocket where id=:id", [id: 1], 0, 10)
        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            println sql.rows("select * from sprocket where id=:id", [id: id], 0, 10)
        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            println sql.rows("select * from sprocket where id=?", [id], 0, 10)
        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            println sql.rows("select * from sprocket where id=$id".toString())
        } catch (Exception e) {
            e.printStackTrace()
        }


        render ''
    }
}
