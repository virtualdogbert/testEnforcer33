package test.command

import grails.validation.Validateable

class Test2Command implements  Validateable{
    int n

    static constraints = {
        n min:2, max:10
    }
}
