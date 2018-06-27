package testenforcer33


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
        log.info request.JSON.toString()
        log.info params.toString()

        //don't ever do this:
        //throw new RuntimeException("oops")

        //if(!eulaService.hasAcceptedEula()) {
        //    redirect controller: 'eula'
        //    return false
        //}

        return true
    }

    boolean after() {
        header("X-FRAME-OPTIONS", 'DENY');
        header("X-Content-Type-OPTIONS", "nosniff");
        header("X-XSS-Protection", "1; mode=block");
        header("Vary", "*");
        header("Expires", "-1");
        header("Pragma", "no-cache");
        header("Cache-control", "no-cache, no-store,max-age=0, must-revalidate");
        true
    }

    void afterView() {
        // no-op
    }
}
