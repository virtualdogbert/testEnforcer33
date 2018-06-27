package testenforcer33.interceptors


class SecurityInterceptor {

    SecurityInterceptor() {
        matchAll()
                .excludes(uri: '/')
                .excludes(uri: '/mfa')
                .excludes(uri: '/login/auth')
                .excludes(controller: 'auth')

    }


    boolean before() {
        log.info request.requestURI
        //log.info request.JSON.toString()
        log.info params.toString()

        //don't ever do this:
//        if (request.requestURI.contains('error')) {
//            throw new RuntimeException("oops")
//        }

        //if(!eulaService.hasAcceptedEula()) {
        //    redirect controller: 'eula'
        //    return false
        //}

        return true
    }
}
