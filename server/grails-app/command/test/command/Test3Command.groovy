package test.command

import grails.validation.Validateable

class Test3Command implements  Validateable{

    String testBlackListAnd
    String testBlackListOR
    String testWhiteListAnd
    String testWhiteListOR
    String testNotMatches

    static constraints = {
        testNotMatches notMatches:'(?:auto|initial)'
        testBlackListOR blackListOr:['(?:auto|initial)', '(?:initial|inherit|transparent)']
        testBlackListAnd blackListAnd:['(?:auto|initial)', '(?:initial|inherit|transparent)']
        testWhiteListOR whiteListOr:['(?:auto|initial)', '(?:initial|inherit|transparent)']
        testWhiteListAnd whiteListAnd:['(?:auto|initial)', '(?:initial|inherit|transparent)']
    }
}
