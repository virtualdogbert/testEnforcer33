package testenforcer33

import grails.plugin.springsecurity.annotation.Secured
import html.sanitizer.HtmlSanitizerService

@Secured('permitAll')
class HtmlController {
    HtmlSanitizerService htmlSanitizerService

    def index(String html) {
        render html
    }

    def save(String html) {
        render htmlSanitizerService.sanitize(html)
    }

    def update(String html) {
        if (htmlSanitizerService.isSafe(html)) {
            render html
            return
        }

        render 'HTML passed is unsafe to render'
    }

    def check(String html) {
        List<String> results = htmlSanitizerService.check(html)

        if (!results) {
            render html
            return
        }

        render "HTML passed is unsafe to render as it contains: [${results.join(',')}]"
    }
}
