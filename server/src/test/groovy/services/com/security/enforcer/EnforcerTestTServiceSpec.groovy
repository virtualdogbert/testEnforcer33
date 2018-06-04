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
package services.com.security.enforcer

import com.security.User
import com.security.enforcer.EnforcerService
import com.security.enforcer.EnforcerTestTService
import com.security.enforcer.InstalledEnforcerService
import grails.plugin.springsecurity.SpringSecurityService
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class EnforcerTestTServiceSpec extends Specification implements ServiceUnitTest<EnforcerTestTService>, DataTest {
    EnforcerService enforcerService

    def setup() {
        mockDomain User
        enforcerService = new InstalledEnforcerService()
        enforcerService.grailsApplication = grailsApplication

        enforcerService.springSecurityService = [getCurrentUser: { -> new User() }] as SpringSecurityService

        //This enables Enforcer for unit tests because it is turned off by default.
        grailsApplication.config.enforcer.enabled = true
    }


    void 'test method closureTestingParameter'() {
        when:

            service.enforcerService = enforcerService
            service.closureTestingParameter(5)
        then:
            true
    }

    void 'test method wrapClosureTestingParameter'() {
        when:

            service.enforcerService = enforcerService
            service.wrapClosureTestingParameter(5)
        then:
            true
    }

    void 'test method closureFilterParameter'() {
        when:
            service.enforcerService = enforcerService
            def test = service.closureFilterParameter([1, 2, 3, 4, 5, 6, 7, 8])
        then:
            test == [2, 4, 6, 8]
    }

    void 'test method reinforceClosureTestingParameter'() {
        when:
            service.enforcerService = enforcerService
            service.reinforceClosureTestingParameter(5)
        then:
            true
    }
}
