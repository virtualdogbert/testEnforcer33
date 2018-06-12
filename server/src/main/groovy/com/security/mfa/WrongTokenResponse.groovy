package com.security.mfa

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
class WrongTokenResponse extends Exception { }
