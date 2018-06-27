package test.command.company

import grails.validation.Validateable

class EmployeesCommand implements Validateable {
    String name
    String address


    static constraints = {
        name size: 2..255
        address size: 5..255
    }
}
