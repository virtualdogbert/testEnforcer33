server.session.timeout = 3600 //seconds

grails {
    profile = 'angular'

    codegen {
        defaultPackage = 'com.security'
    }

    gorm {
        reactor {
            events = false
        }
    }


    controllers {
        upload {
            maxFileSize = 2000000
            maxRequestSize = 2000000
        }
    }
}

info {
    app {
        name = '@info.app.name@'
        version = '@info.app.version@'
        grailsVersion = '@info.app.grailsVersion@'
    }
}

spring {

    main {
        main['banner-mode'] = 'off'
    }

    groovy {
        template {
            template['check-template-location'] = false
        }
    }
}

endpoints {
    enabled = true
    jmx {
        enabled = true
    }
}

grails {
    mime {

        disable {

            accept {

                header {
                    userAgents = [
                            'Gecko',
                            'WebKit',
                            'Presto',
                            'Trident'
                    ]
                }
            }
        }

        types {
            json = [
                    'application/json',
                    'text/json'
            ]
            hal = [
                    'application/hal+json',
                    'application/hal+xml'
            ]
            xml = [
                    'text/xml',
                    'application/xml'
            ]
            atom = 'application/atom+xml'
            css = 'text/css'
            csv = 'text/csv'
            js = 'text/javascript'
            rss = 'application/rss+xml'
            text = 'text/plain'
            all = '*/*'
        }
    }

    urlmapping {
        cache {
            maxsize = 1000
        }
    }

    controllers {
        defaultScope = 'singleton'
    }

    converters {
        encoding = 'UTF-8'
    }
}

grails {
    cors {
        enabled = true
    }
}

hibernate {
    cache {
        queries = false
        use_second_level_cache = false
        use_query_cache = false
    }
}

dataSource {
    pooled = true
    jmxExport = true
    driverClassName = 'org.h2.Driver'
    username = 'sa'
    password = ''
}

environments {

    development {
        grails.dbconsole.enabled = true
        grails.dbconsole.urlRoot = '/admin/dbconsole'

        dataSource {
            dbCreate = 'create-drop'
            url = 'jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE'
        }
    }

    test {
        dataSource {
            dbCreate = 'update'
            url = 'jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE'
        }
    }

    production {
        dataSource {
            dbCreate = 'none'
            url = 'jdbc:h2:./prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE'
            properties {
                jmxEnabled = true
                initialSize = 5
                maxActive = 50
                minIdle = 5
                maxIdle = 25
                maxWait = 10000
                maxAge = 600000
                timeBetweenEvictionRunsMillis = 5000
                minEvictableIdleTimeMillis = 60000
                validationQuery = 'SELECT 1'
                validationQueryTimeout = 3
                validationInterval = 15000
                testOnBorrow = true
                testWhileIdle = true
                testOnReturn = false
                jdbcInterceptors = 'ConnectionState'
                defaultTransactionIsolation = 2
            }
        }
    }
}

grails.plugin.springsecurity.mfa.filterProcessesUrl = 'mfa'
grails.plugin.springsecurity.providerNames = [
        'mfaAuthenticationProvider',
        'daoAuthenticationProvider',
        'anonymousAuthenticationProvider'
]

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.security.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.security.UserRole'
grails.plugin.springsecurity.authority.className = 'com.security.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/', access: ['permitAll']],
        [pattern: '/logout/**', access: ['ROLE_USER']],
        [pattern: '/error', access: ['permitAll']],
        [pattern: '/index', access: ['permitAll']],
        [pattern: '/index.gsp', access: ['permitAll']],
        [pattern: '/shutdown', access: ['permitAll']],
        [pattern: '/assets/**', access: ['permitAll']],
        [pattern: '/**/js/**', access: ['permitAll']],
        [pattern: '/**/css/**', access: ['permitAll']],
        [pattern: '/**/images/**', access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']],
        [pattern: '/admin/**', access: ['ROLE_ADMIN']],
        [pattern: '/user/**', access: ['ROLE_ADMIN']],
        [pattern: '/role/**', access: ['ROLE_ADMIN']],
        [pattern: '/securityInfo/**', access: ['ROLE_ADMIN']],
        [pattern: '/registrationCode/**', access: ['ROLE_ADMIN']],
        [pattern: '/auditevents/**', access: ['ROLE_ADMIN']],
        [pattern: '/beans/**', access: ['ROLE_ADMIN']],
        [pattern: '/configprops/**', access: ['ROLE_ADMIN']],
        [pattern: '/env/**', access: ['ROLE_ADMIN']], //issue
        [pattern: '/health/**', access: ['ROLE_ADMIN']],
        [pattern: '/info/**', access: ['ROLE_ADMIN']],
        [pattern: '/loggers/**', access: ['ROLE_ADMIN']],
        [pattern: '/metrics/**', access: ['ROLE_ADMIN']],
        [pattern: '/mappings/**', access: ['ROLE_ADMIN']]

]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/assets/**', filters: 'none'],
        [pattern: '/**/js/**', filters: 'none'],
        [pattern: '/**/css/**', filters: 'none'],
        [pattern: '/**/images/**', filters: 'none'],
        [pattern: '/**/favicon.ico', filters: 'none'],
        [pattern: '/**', filters: 'JOINED_FILTERS']
]

command.response.return = false

//def configFIlePath = System.getenv('ENCRYPTION_CONFIG_LOCATION') ?: "file:${userHome}/.grails/.jasypt"
//grails.config.locations = [configFIlePath]

jasypt {
    algorithm = "PBEWITHSHA256AND256BITAES-CBC-BC"
    providerName = "BC"
    password = "<your very secret passphrase>"
    keyObtentionIterations = 1000
}

grails.plugin.springsecurity.rest.token.storage.jwt.secret ='qrD6h8K6S9503Q06Y6Rfk21TErImPYqa'

grails.plugin.springsecurity.rest.token.storage.jwt.expiration = 3600
grails.plugin.springsecurity.rest.logout.endpointUrl = '/api/logout'

grails.plugin.springsecurity.filterChain.chainMap = [
        //Stateless chain
        [
                pattern: '/api/**',
                filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
        ],

        //Traditional, stateful chain
        [
                pattern: '/**',
                filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'
        ]
]