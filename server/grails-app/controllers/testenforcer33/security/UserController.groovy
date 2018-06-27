package testenforcer33.security

import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.qrcode.QrCodeService
import mfa.MfaService

@Secured('ROLE_USER')
class UserController {
    QrCodeService qrCodeService
    MfaService mfaService

    @Secured(['ROLE_USER'])
     def mfa() {
         response.setContentType("image/png")
         response.setHeader('Pragma', 'no-cache')
         response.setHeader('no-cache', 'no-cache')
         OutputStream outputStream = response.getOutputStream()
         qrCodeService.renderPng(mfaService.setupMfa(), 500, outputStream)
         outputStream.close()
     }
}
