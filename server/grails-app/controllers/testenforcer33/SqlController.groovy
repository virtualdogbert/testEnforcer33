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
        String param = "'plastic' OR 1=1"

        try {
            println Sprocket.where { id == id }.list()
        } catch (Exception e) {
            e.printStackTrace()
            //org.springframework.core.convert.ConversionFailedException: Failed to convert from type [java.lang.String] to type [java.lang.Long] for value '1 OR 1=1'; nested exception is java.lang.NumberFormatException: For input string: "1OR1=1"
        }

        try {
            println Sprocket.where { material == param }.list()
        } catch (Exception e) {
            e.printStackTrace()//empty list
        }


        try {
            println Sprocket.findAllById(id)
        } catch (Exception e) {
            e.printStackTrace()
            //groovy.lang.MissingMethodException: No signature of method: com.security.Sprocket.findAllById() is applicable for argument types: (java.lang.String) values: [1 OR 1=1]
        }

        try {
            println Sprocket.findAllByMaterial(param)
        } catch (Exception e) {
            e.printStackTrace()//empty list
        }

        try {
            println Sprocket.findAll("from Sprocket where id = $id")
        } catch (Exception e) {
            e.printStackTrace() // java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Long
        }

        try {
            println Sprocket.findAll("from Sprocket where material = $param")
        } catch (Exception e) {
            e.printStackTrace()//empty list
        }

        try {
            println Sprocket.findAll("from Sprocket where id = $id".toString())
        } catch (Exception e) {
            e.printStackTrace()
// no exception empty result or [com.security.Sprocket : 1, com.security.Sprocket : 2, com.security.Sprocket : 3, com.security.Sprocket : 4]
        }

        try {
            println Sprocket.findAll("from Sprocket where material = $param".toString())
        } catch (Exception e) {
            e.printStackTrace()//[com.security.Sprocket : 1, com.security.Sprocket : 2, com.security.Sprocket : 3, com.security.Sprocket : 4]
        }


        try {
            String query = "from Sprocket where id = $id"
            println Sprocket.findAll(query)
        } catch (Exception e) {
            e.printStackTrace()
            // no exception empty result or [com.security.Sprocket : 1, com.security.Sprocket : 2, com.security.Sprocket : 3, com.security.Sprocket : 4]
        }

        try {
            GString query = "from Sprocket where id = $id"
            println Sprocket.findAll(query)
        } catch (Exception e) {
            e.printStackTrace() // java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Long
        }

        try {
            GString query = "from Sprocket where material = $param"
            println Sprocket.findAll(query)
        } catch (Exception e) {
            e.printStackTrace() // empty result
        }

        try {
            println Sprocket.executeQuery("from Sprocket where id = $id")
        } catch (Exception e) {
            e.printStackTrace() //java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Long
        }

        try {
            println Sprocket.executeQuery("from Sprocket where material = $param")
        } catch (Exception e) {
            e.printStackTrace() // empty result
        }

        try {
            println Sprocket.executeQuery("from Sprocket where id = $id".toString())
        } catch (Exception e) {
            e.printStackTrace()
// no exception empty result or [com.security.Sprocket : 1, com.security.Sprocket : 2, com.security.Sprocket : 3, com.security.Sprocket : 4]
        }

        try {
            println Sprocket.executeQuery("from Sprocket where id = :id", [id: id])
        } catch (Exception e) {
            e.printStackTrace() //java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Long
        }

        try {
            println Sprocket.executeQuery("from Sprocket where material = :material", [material: param])
        } catch (Exception e) {
            e.printStackTrace() //empty result
        }

        try {
            println Sprocket.executeQuery("from Sprocket where id = :id", [id: 1l])
        } catch (Exception e) {
            e.printStackTrace()//empty result OR [com.security.Sprocket : 1]
        }

        Sql sql = new Sql(dataSource)


        try {
            println sql.rows("select * from sprocket where id=$id")
        } catch (Exception e) {
            e.printStackTrace()
            //org.h2.jdbc.JdbcSQLException: Data conversion error converting "1 OR 1=1"; SQL statement: select * from sprocket where id=? [22018-197]
        }

        try {
            println sql.rows("select * from sprocket where material=$param")
        } catch (Exception e) {
            e.printStackTrace() //empty result
        }

        try {
            println sql.rows("select * from sprocket where id=$id")
        } catch (Exception e) {
            e.printStackTrace()
            //org.h2.jdbc.JdbcSQLException: Data conversion error converting "1 OR 1=1"; SQL statement: select * from sprocket where id=? [22018-197]
        }

        try {
            println sql.rows("select * from sprocket where material=$param")
        } catch (Exception e) {
            e.printStackTrace() //empty result
        }

        try {
            println sql.rows("select * from sprocket where id=:id", [id: 1], 0, 10)
        } catch (Exception e) {
            e.printStackTrace() // empty result OR [[ID:1, VERSION:1, MANUFACTURER:Cogswell's Cogs, MATERIAL:plastic, CREATOR_ID:1]]
        }

        try {
            println sql.rows("select * from sprocket where id=:id", [id: id], 0, 10)
        } catch (Exception e) {
            e.printStackTrace()
            //org.h2.jdbc.JdbcSQLException: Data conversion error converting "1 OR 1=1"; SQL statement: select * from sprocket where id=? [22018-197]
        }

        try {
            println sql.rows("select * from sprocket where material=:material", [material: param], 0, 10)
        } catch (Exception e) {
            e.printStackTrace() // empty result
        }

        try {
            println sql.rows("select * from sprocket where id=?", [id], 0, 10)
        } catch (Exception e) {
            e.printStackTrace() //empty result OR [[ID:1, VERSION:1, MANUFACTURER:Cogswell's Cogs, MATERIAL:plastic, CREATOR_ID:1]]
        }

        try {
            println sql.rows("select * from sprocket where material=?", [param], 0, 10)
        } catch (Exception e) {
            e.printStackTrace() //empty result
        }

        try {
            println sql.rows("select * from sprocket where id=$id".toString())
        } catch (Exception e) {
            e.printStackTrace()
            //empty result OR [[ID:1, VERSION:1, MANUFACTURER:Cogswell's Cogs, MATERIAL:plastic, CREATOR_ID:1], [ID:2, VERSION:1, MANUFACTURER:Cogswell's Cogs, MATERIAL:plastic, CREATOR_ID:1], [ID:3, VERSION:0, MANUFACTURER:Spacely Sprockets, MATERIAL:metal, CREATOR_ID:1], [ID:4, VERSION:0, MANUFACTURER:Spacely Sprockets, MATERIAL:metal, CREATOR_ID:1]]
        }

        try {
            String query = "select * from sprocket where id=$id"
            println sql.rows(query)
        } catch (Exception e) {
            e.printStackTrace()
//empty result or [[ID:1, VERSION:1, MANUFACTURER:Cogswell's Cogs, MATERIAL:plastic, CREATOR_ID:1], [ID:2, VERSION:1, MANUFACTURER:Cogswell's Cogs, MATERIAL:plastic, CREATOR_ID:1], [ID:3, VERSION:0, MANUFACTURER:Spacely Sprockets, MATERIAL:metal, CREATOR_ID:1], [ID:4, VERSION:0, MANUFACTURER:Spacely Sprockets, MATERIAL:metal, CREATOR_ID:1]]

        }


        try {
            String query = "select * from sprocket where material=$param"
            println sql.rows(query)
        } catch (Exception e) {
            e.printStackTrace() // [[ID:1, VERSION:1, MANUFACTURER:Cogswell's Cogs, MATERIAL:plastic, CREATOR_ID:1], [ID:2, VERSION:1, MANUFACTURER:Cogswell's Cogs, MATERIAL:plastic, CREATOR_ID:1], [ID:3, VERSION:0, MANUFACTURER:Spacely Sprockets, MATERIAL:metal, CREATOR_ID:1], [ID:4, VERSION:0, MANUFACTURER:Spacely Sprockets, MATERIAL:metal, CREATOR_ID:1]]
        }

        try {
            GString query = "select * from sprocket where id=$id"
            println sql.rows(query)
        } catch (Exception e) {
            e.printStackTrace()
            //org.h2.jdbc.JdbcSQLException: Data conversion error converting "1 OR 1=1"; SQL statement: select * from sprocket where id=? [22018-197]
        }

        try {
               GString query = "select * from sprocket where material=$param"
               println sql.rows(query)
           } catch (Exception e) {
               e.printStackTrace()//empty result
           }


        render ''
    }
}
