package test.command

import grails.validation.Validateable

class Test4Command implements  Validateable{
    String             name
    int                number
    Map t2

    static constraints = {
        name nullable:true, size: 5..15
        number nullable:true
    }
}
