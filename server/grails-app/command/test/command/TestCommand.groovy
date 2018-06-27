package test.command

import grails.validation.Validateable

class TestCommand implements  Validateable{
    Integer id
    String             name
    int                number
    List<Test2Command> t2

    static constraints = {
        id min: 1
        name nullable:true, size: 5..15
        number nullable:true
        t2 cascade: true, nullable: true
    }
}
