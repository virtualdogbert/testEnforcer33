package test.command.company

import grails.validation.Validateable

class CompanyCommand implements  Validateable{
    String name
    String description
    List<String> tags
    List<DivisionsCommand> divisions
    List<EmployeesCommand> employees


    static constraints = {
        name size:  5..255
        description size: 0..4000
        employees cascade:true
        divisions cascade:true
    }
}
