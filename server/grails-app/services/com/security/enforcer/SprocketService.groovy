package com.security.enforcer

import com.security.Sprocket
import grails.gorm.services.Service

@Service(Sprocket)
interface SprocketService {
    Sprocket getSprocket(Long id)

    String findSprocketMaterial(Long id)
}
