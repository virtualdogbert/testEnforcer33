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
package com.security.enforcer

import com.security.User
import com.security.enforcer.EnforcerException
import com.security.enforcer.EnforcerService
import com.security.enforcer.InstalledEnforcerService
import com.virtualdogbert.ast.*
import grails.plugin.springsecurity.SpringSecurityService
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class ReinforceAnnotationSpec extends Specification  implements ServiceUnitTest<InstalledEnforcerService>, DataTest {
    EnforcerService enforcerService


    def setup() {
        mockDomain User
        enforcerService = new InstalledEnforcerService()
        enforcerService.grailsApplication = grailsApplication

        enforcerService.springSecurityService = [getCurrentUser: { -> new User() }] as SpringSecurityService

        //This enables Enforcer for unit tests because it is turned off by default.
        grailsApplication.config.enforcer.enabled = true
    }

    void 'test method reinforceClosureTrue'() {
        when:
            reinforceClosureTrue()
        then:
            true
    }

    void 'test method reinforceClosureTrueWithFailureClosure'() {
        when:
            reinforceClosureTrueWithFailureClosure()
        then:
            true
    }

    void 'test method reinforceClosureFalseWithFailureClosure'() {
        when:
            reinforceClosureFalseWithFailureClosure()
        then:
            thrown EnforcerException
    }

    void 'test method reinforceClosureTrueWithFailureAndSuccessClosures'() {
        when:
            reinforceClosureTrueWithFailureAndSuccessClosures()
        then:
            true
    }

    void 'test method reinforceClosureFalseWithFailureAndSuccessClosures'() {
        when:
            reinforceClosureFalseWithFailureAndSuccessClosures()
        then:
            thrown EnforcerException
    }

    void 'test class protection'() {
        setup:
            TestEnforcer t = new TestEnforcer()
            t.enforcerService = enforcerService
        when:
            t.clazzProtectedMethod1()
        then:
            thrown EnforcerException
        when:
            t.clazzProtectedMethod2()
        then:
            thrown EnforcerException
        when:
            t.methodProtectedMethod1()
        then:
            true
    }

    void 'test method reinforceFilter'() {
        when:
            def returnedList = reinforceFilter()
        then:
            returnedList == [2, 4, 6, 8]
    }

    void 'test method reinforceFilterT'() {
        when:
            def returnedList = reinforceFilterT()
        then:
            returnedList == [2, 4, 6, 8]
    }

    void 'test method reinforceFilterS'() {
        when:
            def returnedList = reinforceFilterS()
        then:
            returnedList == [2, 4, 6, 8]
    }

    void 'test method reinforceFilterSE'() {
        when:
            def returnedList = reinforceFilterSE()
        then:
            returnedList == [2, 4, 6, 8]
    }

    void 'test method reinforceFilterTS'() {
        when:
            def returnedList = reinforceFilterTS()
        then:
            returnedList == [2, 4, 6, 8]
    }

    @Reinforce({ true })
    def reinforceClosureTrue() {
        println 'nice'
    }

    @Reinforce({ false })
    def reinforceClosureFalse() {
        println 'nice'
    }

    @Reinforce(value = { true }, failure = { throw new EnforcerException("not nice") })
    def reinforceClosureTrueWithFailureClosure() {
        println 'nice'
    }

    @Reinforce(value = { false }, failure = { throw new EnforcerException("nice") })
    def reinforceClosureFalseWithFailureClosure() {
        throw new Exception("this shouldn't happen on closureFalseWithFailureClosure")
    }

    @Reinforce(value = { true }, failure = { throw new EnforcerException("not nice") }, success = { println "nice" })
    def reinforceClosureTrueWithFailureAndSuccessClosures() {

    }

    @Reinforce(value = { false }, failure = { throw new EnforcerException("nice") }, success = { println "not nice" })
    def reinforceClosureFalseWithFailureAndSuccessClosures() {
        throw new Exception("this shouldn't happen on closureFalseWithFailureAndSuccessClosures")
    }

    @Reinforce({ false })
    class TestEnforcer {
        EnforcerService enforcerService

        @Reinforce(value = { false }, failure = { throw new EnforcerException("nice") })
        def clazzProtectedMethod1() {
            println 'not nice'
        }

        def clazzProtectedMethod2() {
            println 'not nice'
        }

        @Reinforce({ true })
        def methodProtectedMethod1() {
            println 'nice'
        }
    }

    @ReinforceFilter({ Object o -> (o as List).findResults { it % 2 == 0 ? it : null } })
    List<Integer> reinforceFilter() {
        [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }


    @ReinforceFilterT({ Object o -> (o as List).findResults { it % 2 == 0 ? it : null }
    })
    List<Integer> reinforceFilterT() {
        [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }


    @ReinforceFilterS({ Object o -> (o as List).findResults { it % 2 == 0 ? it : null }
    })
    List<Integer> reinforceFilterS() {
        [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    @ReinforceFilterS(value = { Object o -> (o as List).findResults { it % 2 == 0 ? it : null }
    }, extensions = ['org.grails.compiler.ValidateableTypeCheckingExtension',
            'org.grails.compiler.NamedQueryTypeCheckingExtension',
            'org.grails.compiler.HttpServletRequestTypeCheckingExtension',
            'org.grails.compiler.WhereQueryTypeCheckingExtension',
            'org.grails.compiler.DynamicFinderTypeCheckingExtension',
            'org.grails.compiler.DomainMappingTypeCheckingExtension',
            'org.grails.compiler.RelationshipManagementMethodTypeCheckingExtension'])
    List<Integer> reinforceFilterSE() {
        [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }


    @ReinforceFilterTS({ Object o -> (o as List).findResults { it % 2 == 0 ? it : null }
    })
    List<Integer> reinforceFilterTS() {
        [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
