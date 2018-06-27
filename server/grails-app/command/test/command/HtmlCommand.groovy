package test.command

import grails.validation.Validateable

class HtmlCommand implements  Validateable{
    String htmlText

    static constraints = {
        htmlText htmlEnforce:true, minSize: 10
    }
}
