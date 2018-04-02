package com.virtualdogbert

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Set<Role> getAuthorities() {
        Set<Role> roles = (Set<Role>)[]
        Set<RoleGroup> groups = (UserRoleGroup.findAllByUser(this) as List<UserRoleGroup>)*.roleGroup as Set<RoleGroup>

        groups.each {RoleGroup group ->
            roles.addAll(group.authorities)
        }

        roles.addAll(((List<UserRole>)UserRole.findAllByUser(this))*.role)

        return roles
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
    }

    static mapping = {
	    password column: '`password`'
    }
}
