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


import com.security.Sprocket
import com.virtualdogbert.ast.Enforce
import grails.transaction.Transactional
import groovy.transform.CompileStatic


/**
 * The EnforcerService has one enforce method for enforcing business rules, and is extended by the traits it implements.
 */
@Transactional
@CompileStatic
class TestService implements RoleTrait, DomainRoleTrait, CreatorTrait {

    @Enforce({isCreator(sp)})
    Sprocket getSprocket(Sprocket sp) {
        return sp
    }
}
