package html.sanitizer

import com.security.html.sanitizer.HtmlChangeListener
import grails.core.GrailsApplication
import groovy.transform.CompileStatic
import org.owasp.html.PolicyFactory

@CompileStatic
class HtmlSanitizerService {
    GrailsApplication grailsApplication

    String sanitize(String html) {
        PolicyFactory htmlPolicy = (PolicyFactory) grailsApplication.config.html_sanitizer_policy
        String sanitizedHtml = htmlPolicy.sanitize(html)
        return sanitizedHtml
    }

    boolean isSafe(String html) {
        PolicyFactory htmlPolicy = (PolicyFactory) grailsApplication.config.html_sanitizer_policy
        List<String> results = []
        htmlPolicy.sanitize(html, new HtmlChangeListener(), results)
        results.isEmpty()
    }

    List<String> check(String html) {
        PolicyFactory htmlPolicy = (PolicyFactory) grailsApplication.config.html_sanitizer_policy
        List<String> results = []
        htmlPolicy.sanitize(html, new HtmlChangeListener(), results)
        return results
    }
}
