/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package com.enforcer

import com.security.Sprocket
import com.security.enforcer.DomainRole
import com.security.enforcer.EnforcerService
import com.virtualdogbert.Role
import com.virtualdogbert.RoleGroup
import com.virtualdogbert.User
import com.virtualdogbert.UserRole
import com.virtualdogbert.UserRoleGroup
import com.virtualdogbert.ast.EnforcerException
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.util.Holders
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */

@Mock([Role, User, UserRole, RoleGroup, UserRoleGroup, DomainRole, Sprocket])
@TestFor(EnforcerService)
class EnforcerServiceSpec extends Specification {

    User testUser, testUser2

    def setup() {
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true, failOnError: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true, failOnError: true)
        testUser = new User(username: 'me', password: 'password').save(flush: true, failOnError: true)
        testUser2 = new User(username: 'me2', password: 'password').save(flush: true, failOnError: true)

        UserRole.create testUser, adminRole, true
        UserRole.create testUser, userRole, true

        UserRole.create testUser2, userRole, true

        service.springSecurityService = [getCurrentUser: { -> testUser }] as SpringSecurityService

        grailsApplication.config.enforcer.enabled = true
        //This enables Enforcer for unit tests because it is turned off by default.
        service.grailsApplication = grailsApplication

        if(!Holders.grailsApplication.mainContext.containsBean("enforcerService")) {
            Holders.grailsApplication.mainContext.beanFactory.registerSingleton("enforcerService", service)
        }
    }

    //Testing EnforcerService
    void 'test enforce { true }'() {
        when:
            service.enforce({ true })
        then:
            true
    }

    void 'test enforce { false }'() {
        when:
            service.enforce({ false })
        then:
            EnforcerException e = thrown()
            e.message == 'Access Denied'
    }

    void 'test enforce { true }, { throw new EnforcerException("not nice") }'() {
        when:
            service.enforce({ true }, { throw new EnforcerException("not nice") })
        then:
            true
    }

    void 'test enforce { false }, { throw new EnforcerException("nice") }'() {
        when:
            service.enforce({ false }, { throw new EnforcerException("nice") })
        then:
            thrown EnforcerException
    }

    void 'test enforce { true }, { throw new EnforcerException("not nice")}, { println "nice" }'() {
        when:
            service.enforce({ true }, { throw new EnforcerException("not nice") }, { println "nice" })
        then:
            true
    }

    void 'test enforce { false }, { throw new EnforcerException("nice") }, { throw new EnforcerException("not nice") }'() {
        when:
            service.enforce({ false }, { throw new EnforcerException("nice") }, { println("not nice") })
        then:
            thrown EnforcerException
    }


    //Testing DomainRoleTrait
    void 'test enforce hasDomainRole("owner", domainObject, testUser)'() {
        when:
            Sprocket sprocket = new Sprocket(material: 'metal', creator: testUser).save(failOnError: true)
            service.changeDomainRole('owner', sprocket, testUser)
            service.enforce({ hasDomainRole('owner', sprocket, testUser) })
        then:
            true
    }

    void 'test fail enforce hasDomainRole("owner",domainObject, testUser)'() {
        when:
            Sprocket sprocket = new Sprocket(material: 'metal', creator: testUser).save(failOnError: true)
            service.changeDomainRole('owner', sprocket, testUser)
            service.enforce({ hasDomainRole('owner', sprocket, testUser2) })
        then:
            thrown EnforcerException
    }


    //Testing RoleTrait
    void 'test enforce hasRole("ROLE_ADMIN", testUser)'() {
        when:
            service.enforce({ hasRole('ROLE_ADMIN', testUser) })
        then:
            true
    }

    void 'test enforce hasRole("ROLE_USER", testUser)'() {
        when:
            service.enforce({ hasRole('ROLE_USER', testUser) })
        then:
            true
    }

    void 'test enforce hasRole("ROLE_SUPER_USER", testUser)'() {
        when:
            service.enforce({ hasRole('ROLE_SUPER_USER', testUser) })
        then:
            thrown EnforcerException
    }
}

