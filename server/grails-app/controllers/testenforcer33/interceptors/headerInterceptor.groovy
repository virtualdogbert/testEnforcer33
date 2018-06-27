package testenforcer33.interceptors


class headerInterceptor {

    headerInterceptor() {
        matchAll()

    }


    boolean after() {
        header("X-FRAME-OPTIONS", 'DENY');
        header("X-Content-Type-OPTIONS", "nosniff");
        header("X-XSS-Protection", "1; mode=block");
        header("X-Content-Type-Options", "nosniff")
        header("Vary", "*");
        header("Expires", "-1");
        header("Pragma", "no-cache");
        header("Cache-control", "no-cache, no-store,max-age=0, must-revalidate");
        true
    }

}
